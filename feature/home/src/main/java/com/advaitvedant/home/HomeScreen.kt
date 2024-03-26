package com.advaitvedant.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.design.component.OpTopAppBar
import com.advaitvedant.design.icon.OpIcons
import com.advaitvedant.model.PhoneticLesson
import com.advaitvedant.model.LessonState
import com.advaitvedant.ui.LessonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onLessonClick: (Int) -> Unit, viewModel: HomeViewModel = hiltViewModel()
) {
    val lessonListUiState by viewModel.lessonListState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        OpTopAppBar(title = stringResource(R.string.openphonics))
        HomeScreen(onLessonClick = onLessonClick, lessonList = lessonListUiState)
    }
}

@Composable
fun HomeScreen(
    onLessonClick: (Int) -> Unit, lessonList: LessonListUiState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (lessonList) {
            LessonListUiState.Loading -> Unit
            is LessonListUiState.Success -> {
                lessonList(
                    phoneticLessons = lessonList.phoneticLessons,
                    onLessonClick = onLessonClick
                )
            }
        }
    }
}

fun LazyListScope.lessonList(
    phoneticLessons: List<PhoneticLesson>, onLessonClick: (Int) -> Unit
) {
    items(items = phoneticLessons, key = { it.id }) {
        when (it.state) {
            LessonState.LOCKED -> {
                LockedCard(lessonNumber = it.num,
                    phonetic = it.phonetic,
                    onClick = { onLessonClick(it.id) })
            }

            LessonState.PROGRESS -> {
                InProgressCard(lessonNumber = it.num,
                    phonetic = it.phonetic,
                    onClick = { onLessonClick(it.id) })
            }

            LessonState.COMPLETED -> {
                FinishedCard(lessonNumber = it.num,
                    phonetic = it.phonetic,
                    onClick = { onLessonClick(it.id) })
            }
        }
    }
}

@Composable
fun LockedCard(
    lessonNumber: Int, phonetic: String, onClick: () -> Unit
) {
    LessonCard(
        lessonNumber = lessonNumber,
        phonetic = phonetic,
        icon = OpIcons.Lock,
        color = MaterialTheme.colorScheme.surfaceVariant,
        buttonText = stringResource(R.string.locked),
        onClick = onClick
    )
}

@Composable
fun InProgressCard(
    lessonNumber: Int, phonetic: String, onClick: () -> Unit
) {
    LessonCard(
        lessonNumber = lessonNumber,
        phonetic = phonetic,
        icon = OpIcons.Play,
        color = MaterialTheme.colorScheme.primary,
        buttonText = stringResource(R.string.cont),
        onClick = onClick
    )
}

@Composable
fun FinishedCard(
    lessonNumber: Int, phonetic: String, onClick: () -> Unit
) {
    LessonCard(
        lessonNumber = lessonNumber,
        phonetic = phonetic,
        icon = OpIcons.Check,
        color = MaterialTheme.colorScheme.secondary,
        buttonText = stringResource(R.string.practice),
        onClick = onClick
    )
}