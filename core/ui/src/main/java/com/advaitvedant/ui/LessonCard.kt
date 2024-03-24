package com.advaitvedant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.advaitvedant.design.component.OpBackground
import com.advaitvedant.design.icon.OpIcons
import com.advaitvedant.design.theme.OpTheme

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
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
                    imageVector = OpIcons.Play,
                    contentDescription = "check icon",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "LESSON 1",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "a", style = MaterialTheme.typography.titleLarge, color = Color.White
                    )
                }
            }
            Button(onClick = onClick, shape = RoundedCornerShape(6.dp)) {
                Text("CONTINUE", style = MaterialTheme.typography.titleSmall, color = Color.White)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LessonCardPreview(){
//    OpTheme(darkTheme = true) {
//        OpBackground {
//            LessonCard(
////                lesson = Lesson(
////                    id = 1,
////                    num  = 1,
////                    phonetic = "a, b, c",
////                    state = LessonState.LOCKED
////                ),
////                onClick = {},
////                onLessonClick = {}
//            )
//        }
//    }
//}
//@Preview
//@Composable
//fun Som(){
//    OpTheme(darkTheme = true) {
//        OpBackground {
//            Column(modifier = Modifier.fillMaxSize()){
//                Text("HOHOHHO")
//            }
//        }
//    }
//}
@Preview
@Composable
fun LessonCardIcon() {
    OpTheme(darkTheme = true) {
        OpBackground {
            Column {

                Icon(
                    imageVector = OpIcons.Play,
                    contentDescription = "check icon",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
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
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)){
                items(10) { index ->
                    LessonCard {

                    }
                }
            }
        }
    }
}