package com.advaitvedant.lesson

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.DataRepository
import com.advaitvedant.audioplayer.SoundManager
import com.advaitvedant.audioplayer.SoundPlayer
import com.advaitvedant.model.LessonWithData
import com.advaitvedant.speechtotext.SpeechToTextParser
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
    val stt: SpeechToTextParser,
    @ApplicationContext val context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val sttState = stt.state
    private val lessonArgs: LessonArgs = LessonArgs(savedStateHandle)
    private val enmp3: (String) -> String= {text -> "https://storage.googleapis.com/openphonics.appspot.com/words/en/$text" }
    private val tamp3: (String) -> String= {text -> "https://storage.googleapis.com/openphonics.appspot.com/words/ta/$text" }
    private val dir = File(context.filesDir, lessonId.toString()).absolutePath
    private val lessonId
        get() = lessonArgs.lesson
    val loading: StateFlow<Boolean>
        get() = soundPlayer.loadedAllSounds

    init {
        viewModelScope.launch {
            sttState.collect {
                Log.d("SPEECHTOTETX", sttState.value.text)
            }
        }
    }
    fun start(){
        stt.startListening()
    }
    fun stop(){
        stt.stopListening()
    }

    fun reset(){
        stt.resetText()
    }
    val lessonState: StateFlow<LessonUiState> =
        data.lesson(lessonId)
            .map {
                soundManager.downloadBatch(lessonId, it.words.map { word -> Pair(word.text+".mp3", enmp3(word.text))})
                soundManager.downloadBatch(lessonId, it.words.map { word -> Pair(word.translation+".mp3", tamp3(word.translation))})
                it.words.forEach { word ->
                    soundPlayer.preload("$dir/${word.text}.mp3")
                    soundPlayer.preload("$dir/${word.translation}.mp3")
                }
                LessonUiState.Success(it)
            }
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