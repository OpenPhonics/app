package com.advaitvedant.lesson

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.DataRepository
import com.advaitvedant.audioplayer.SoundManager
import com.advaitvedant.audioplayer.SoundPlayer
import com.advaitvedant.model.LessonWithData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    val data: DataRepository,
    val soundManager: SoundManager,
    val soundPlayer: SoundPlayer,
    @ApplicationContext val context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val lessonArgs: LessonArgs = LessonArgs(savedStateHandle)
    init {
        viewModelScope.launch {
            soundManager.downloadBatch(
                lessonId,
                listOf(
                    Pair(
                        "then.mp3",
                        "https://storage.googleapis.com/openphonics.appspot.com/words/en/Then.mp3"
                    )
                )
            )
        }
        val dir = File(context.filesDir, lessonId.toString())

        soundPlayer.preload(File(dir, "then.mp3").absolutePath)

        soundPlayer.preload(R.raw.then)
    }
    private val lessonId
        get() = lessonArgs.lesson
    val loading: StateFlow<Boolean>
        get() = soundPlayer.loadedAllSounds
    fun playSound(){
        val dir = File(context.filesDir, lessonId.toString())
        viewModelScope.launch {
            soundPlayer.enqueue(File(dir, "then.mp3").absolutePath)
//            delay(500L)
            soundPlayer.enqueue(File(dir, "then.mp3").absolutePath)
//            delay(500L)
            soundPlayer.enqueue(File(dir, "then.mp3").absolutePath)
//            delay(500L)
            soundPlayer.enqueue(File(dir, "then.mp3").absolutePath)
        }

        soundPlayer.enqueue(R.raw.then)
        soundPlayer.enqueue(R.raw.then)
    }

    val lessonState: StateFlow<LessonUiState> =
        data.lesson(lessonId)
            .map(LessonUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = LessonUiState.Loading,
            )
}

sealed interface LessonUiState {
    data object Loading : LessonUiState

    data class Success(
        val lesson: LessonWithData
    ) : LessonUiState
}