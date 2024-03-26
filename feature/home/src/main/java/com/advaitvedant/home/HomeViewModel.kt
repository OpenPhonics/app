package com.advaitvedant.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.DataRepository
import com.advaitvedant.model.PhoneticLesson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val data: DataRepository
) : ViewModel() {

    val lessonListState: StateFlow<LessonListUiState> = data.lessons()
        .map(LessonListUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LessonListUiState.Loading,
        )
}

sealed interface LessonListUiState {
    data object Loading : LessonListUiState

    data class Success(
        val phoneticLessons: List<PhoneticLesson>
    ) : LessonListUiState
}