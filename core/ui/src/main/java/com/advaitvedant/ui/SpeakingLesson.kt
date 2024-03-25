package com.advaitvedant.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.advaitvedant.design.component.OpBackground
import com.advaitvedant.design.theme.OpTheme

enum class AnswerState {
    NOT_ANSWERED,
    WRONG,
    CORRECT
}

@Composable
fun SpeakingLesson(
    modifier: Modifier = Modifier,
    word: String,
    onWordClick: () -> Unit,
    translate: String,
    onTranslateClick: () -> Unit,
    onMicPressed: () -> Unit,
    onMicReleased: () -> Unit,
    answer: AnswerState = AnswerState.CORRECT,
    onContinueClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextButton(onClick = onWordClick) {
                Text(word, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }
            Spacer(modifier = Modifier)
            TextButton(onClick = onTranslateClick) {
                Text(translate, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }
        }
        RoundedHoldIconButton(
            size = 0.5f,
            icon = Icons.Rounded.Mic,
            enabled = true,
            onPressed = onMicPressed,
            onReleased = onMicReleased,
            backgroundColor = MaterialTheme.colorScheme.primary
        )
        when (answer) {
            AnswerState.NOT_ANSWERED -> {
                LessonButtonNotAnswered(onContinueClick = onContinueClick)
            }

            AnswerState.CORRECT -> {
                LessonButtonCorrect(onContinueClick = onContinueClick)
            }

            AnswerState.WRONG -> {
                LessonButtonWrong(onContinueClick = onContinueClick)
            }
        }

    }
}

@Composable
fun LessonButtonNotAnswered(
    onContinueClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .widthIn(0.dp, 400.dp)
            .fillMaxWidth()
            .padding(12.dp),
        onClick = onContinueClick,
        shape = RoundedCornerShape(12.dp),
        enabled = false,
    ) {
        Row(modifier = Modifier.padding(6.dp)) {
            Text(text = "CONTINUE", fontSize = MaterialTheme.typography.titleMedium.fontSize)
        }
    }
}

@Composable
fun LessonButtonCorrect(
    onContinueClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .widthIn(0.dp, 400.dp)
            .fillMaxWidth()
            .padding(12.dp),
        onClick = onContinueClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Row(modifier = Modifier.padding(6.dp)) {
            Text(text = "CONTINUE", fontSize = MaterialTheme.typography.titleMedium.fontSize)
        }
    }
}
@Composable
fun LessonButtonWrong(
    onContinueClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .widthIn(0.dp, 400.dp)
            .fillMaxWidth()
            .padding(12.dp),
        onClick = onContinueClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
        )
    ) {
        Row(modifier = Modifier.padding(6.dp)) {
            Text(text = "GOT IT", fontSize = MaterialTheme.typography.titleMedium.fontSize)
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
            .fillMaxHeight(0.2f)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(3.dp, iconColor, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
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
        if (isPressed) {
            Text("Listening", color = iconColor, fontSize = MaterialTheme.typography.titleMedium.fontSize)
        } else {
            Icon(
                modifier = Modifier
                    .fillMaxSize(0.75f),
                imageVector = icon,
                contentDescription = null,
                tint = if (enabled) iconColor else iconColor.copy(0.5f)
            )
        }
    }
}

@Preview
@Composable
fun SpeakingLessonPreview() {
    OpTheme {
        OpBackground {
            SpeakingLesson(
                word = "Hello",
                onWordClick = {},
                translate = "Hola",
                onTranslateClick = {},
                onMicPressed = {},
                onMicReleased = {},
                onContinueClick = {},
                answer = AnswerState.WRONG
            )
        }
    }
}