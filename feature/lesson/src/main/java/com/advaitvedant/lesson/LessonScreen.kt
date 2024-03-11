package com.advaitvedant.lesson

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MinorCrash
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LessonRoute(
    viewModel: LessonViewModel = hiltViewModel(),
    onBackClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment =  Alignment.CenterHorizontally){
        val loading = viewModel.loading.collectAsStateWithLifecycle()
        if (loading.value) {
            Button(onClick = viewModel::reset) {
                Text("CLICK ME")
            }
            RoundedHoldIconButton(size = 0.5f, icon = Icons.Default.MinorCrash, enabled = true, onPressed = viewModel::start, onReleased = viewModel::stop)
        } else {
            Text("LODAING")
        }
        
        val context = LocalContext.current
        val multiplePermissionsLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            permission.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
            }
        }
        Button(onClick = {
            multiplePermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO
                )
            )
        }) {

        }
    }
}

@Composable
fun RoundedHoldIconButton(
    modifier: Modifier = Modifier,
    size: Float,
    icon: ImageVector,
    enabled: Boolean,
    backgroundColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    iconColor: Color = MaterialTheme.colorScheme.background,
    onPressed: () -> Unit,
    onReleased: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    // Animate the button size
    val buttonSize by animateFloatAsState(
        targetValue = if (isPressed) size * 0.9f else size,
        animationSpec = spring(stiffness = Spring.StiffnessHigh)
    )

    Box(
        modifier = modifier
            .fillMaxWidth(buttonSize)
            .aspectRatio(1f)
            .clip(CircleShape)
            .border(3.dp, iconColor, CircleShape)
            .clip(CircleShape)
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (enabled) {
                            isPressed = true
                            onPressed()
                            awaitRelease()
                            isPressed = false
                            onReleased()
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize(0.75f),
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) iconColor else iconColor.copy(0.5f)
        )
    }
}