package com.advaitvedant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.advaitvedant.design.component.OpBackground
import com.advaitvedant.design.icon.OpIcons
import com.advaitvedant.design.theme.OpTheme


@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    lessonNumber: Int,
    phonetic: String,
    icon: ImageVector,
    color: Color,
    buttonText: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = "type",
                    modifier = Modifier.size(48.dp),
                    tint = color
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.lesson, lessonNumber),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = phonetic,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Button(
                modifier = Modifier.padding(start = 12.dp).widthIn(min = 120.dp),
                onClick = onClick, shape = RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(containerColor = color)) {
                Text(
                    textAlign = TextAlign.Center,
                    text = buttonText,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
            }
        }
    }
}


@Preview
@Composable
fun ElevatedCardList() {
    OpTheme(darkTheme = true) {
        OpBackground {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(10) { index ->
                    LessonCard(
                        lessonNumber = index + 1,
                        phonetic = "a",
                        icon = OpIcons.Check,
                        color = MaterialTheme.colorScheme.primary,
                        buttonText = stringResource(id = R.string.cont)
                    ) {

                    }
                }
            }
        }
    }
}