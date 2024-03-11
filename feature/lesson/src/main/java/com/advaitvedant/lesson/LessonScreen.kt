package com.advaitvedant.lesson

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LessonRoute(
    viewModel: LessonViewModel = hiltViewModel(),
    onBackClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        val loading = viewModel.loading.collectAsStateWithLifecycle()
        if (loading.value) {
            Button(onClick = viewModel::playSound) {
                Text("CLICK ME")
            }
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