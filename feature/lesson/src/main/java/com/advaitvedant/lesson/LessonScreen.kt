package com.advaitvedant.lesson

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.model.Word
import kotlinx.coroutines.launch

@Composable
fun LessonRoute(
    viewModel: LessonViewModel = hiltViewModel(),
    onBackClick: () -> Unit){
    val audioRecordingPermissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { permissionGranted ->
        if (permissionGranted){
            viewModel.start()
        }
    }

    val lessonUiState = viewModel.lessonState.collectAsStateWithLifecycle()
    val soundsLoaded = viewModel.soundsLoaded.collectAsStateWithLifecycle()
    LessonScreen(
        onBackClick = onBackClick,
        startRecording = { audioRecordingPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)},
        endRecording = viewModel::stop,
        isLoadingSound = !soundsLoaded.value,
        lessonState = lessonUiState.value,
        reset = viewModel::reset,
        playSound = viewModel::playSound
    )
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    onBackClick: () -> Unit,
    startRecording: ()-> Unit,
    endRecording: () -> Unit,
    isLoadingSound: Boolean,
    lessonState: LessonUiState,
    reset: () -> Unit,
    playSound: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val isLoadingLesson = lessonState is LessonUiState.Loading
    if (isLoadingLesson || isLoadingSound) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading ...")
        }

    } else {
        when (lessonState) {
            LessonUiState.Loading -> Unit
            is LessonUiState.Success -> {
                val pagerState = rememberPagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0f,
                    pageCount = { lessonState.lesson.words.size }
                )
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        reset()
                    }
                }
                Scaffold(
                    topBar = {
                             TopAppBar(
                                 navigationIcon = {
                                  IconButton(onClick = onBackClick) {
                                      Image(imageVector = Icons.Default.Close, contentDescription = "Close")
                                  }
                                 },title = {
                                 Text("Lesson ${lessonState.lesson.num}")
                             })
                    },
                    floatingActionButton = {
                        LearnActionButtons(
                            startRecording = startRecording,
                            endRecording = endRecording,
                            forward = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage + 1
                                    )
                                }
                            },
                            backward = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage - 1
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        LearnContent(
                            pagerState = pagerState,
                            words = lessonState.lesson.words,
                            soundText = playSound
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun LearnActionButtons(
    startRecording: () -> Unit,
    endRecording: () -> Unit,
    forward: () -> Unit,
    backward: () -> Unit
){
    val pagerWidth = 0.6f
    val micHeight = 0.25f
    Box(
        modifier = Modifier
            .fillMaxWidth(pagerWidth)
            .aspectRatio(pagerWidth / micHeight),
        contentAlignment = Alignment.Center
    ) {

        HorizontalPagerNavigator(
            onClickFront = forward,
            onClickBack = backward,
            width = pagerWidth/pagerWidth,
            iconColor = MaterialTheme.colorScheme.primary
        )
        RoundedHoldIconButton(
            size = micHeight/pagerWidth,
            icon = Icons.Default.Mic,
            enabled = true,
            onPressed = startRecording,
            onReleased = endRecording,
            iconColor = MaterialTheme.colorScheme.primary
        )
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LearnContent(
    pagerState: PagerState,
    words: List<Word>,
    soundText: (String) -> Unit,
){
    HorizontalPager(
        state = pagerState
    ) { index ->
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val text = words[index].text
            val translation = words[index].translation
            TextButton(onClick = { soundText(text)}) {
                Text(text, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            }
            Spacer(modifier = Modifier)
            TextButton(onClick = { soundText(translation)}) {
                Text(translation, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            }
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

@Composable
fun HorizontalPagerNavigator(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickFront: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    iconColor: Color = MaterialTheme.colorScheme.background,
    width: Float
){
    Box(
        modifier = modifier
            .fillMaxWidth(width)
            .clip(RoundedCornerShape(100))
            .background(
                backgroundColor
            )
            .padding(8.dp)
    ) {
        IconButton(
            onClick = {
                onClickBack()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Go back",
                tint = iconColor
            )
        }
        IconButton(
            onClick = {
                onClickFront()
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go forward",
                tint = iconColor
            )
        }
    }
}