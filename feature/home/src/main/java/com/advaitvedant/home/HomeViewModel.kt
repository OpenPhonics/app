package com.advaitvedant.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitvedant.model.Lesson
import com.advaitvedant.model.LessonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val lessonListState: StateFlow<LessonListUiState> = flow {
        val lessons = mutableListOf<Lesson>()
        val phoneticAlphabet = ('a'..'z').toList()

        repeat(100) { index ->
            val num = index + 1
            val phonetic = phoneticAlphabet[index % phoneticAlphabet.size].toString()
            val state = when {
                index < 6 -> LessonState.COMPLETED
                index == 6 -> LessonState.PROGRESS
                else -> LessonState.LOCKED
            }
            lessons.add(Lesson(num, num, phonetic, state))
        }
        delay(2000)
        emit(lessons)
    }.map(LessonListUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LessonListUiState.Loading,
        )
}

sealed interface LessonListUiState {
    data object Loading : LessonListUiState

    data class Success(
        val lessons: List<Lesson>
    ) : LessonListUiState
}