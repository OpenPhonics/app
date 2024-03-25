package com.advaitvedant.lesson

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advaitevdant.data.repository.DataRepository
import com.advaitvedant.audioplayer.SoundManager
import com.advaitvedant.audioplayer.SoundPlayer
import com.advaitvedant.model.LessonWithData
import com.advaitvedant.speechtotext.SpeechToTextParser
import com.advaitvedant.ui.AnswerState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val enmp3: (String) -> String= { text -> "https://storage.googleapis.com/openphonics.appspot.com/words/en/$text" }
    private val tamp3: (String) -> String= { text -> "https://storage.googleapis.com/openphonics.appspot.com/words/ta/$text" }
    private val dir = File(context.filesDir, lessonId.toString()).absolutePath
    private val _word: MutableStateFlow<String> = MutableStateFlow("")
    private val _answerState = MutableStateFlow(AnswerState.NOT_ANSWERED)
    private val _listeningState = MutableStateFlow(ListeningState.NOT_LISTENING)
    val answerState = _answerState.asStateFlow()

    private val lessonId
        get() = lessonArgs.lesson

    val soundsLoaded: StateFlow<Boolean> = soundPlayer.loadedAllSounds

    init {
        soundPlayer.preload(R.raw.correct_sound)
        soundPlayer.preload(R.raw.wrong_sound)
        viewModelScope.launch {
            sttState.collect {
//                if (it.text.isBlank()){
//                    if (_listeningState.value == ListeningState.FINISHED_LISTENING){
//                        _listeningState.value = ListeningState.NOT_LISTENING
//                    }
//                    return@collect
//                }
                if (it.text.contains(_word.value, ignoreCase = true)){
                    _answerState.value = AnswerState.CORRECT
                } else {
                    _answerState.value = AnswerState.WRONG
                }
            }
        }
    }

    fun setWord(word: String){
        this._word.value = word
    }
    fun start(){
        _listeningState.value = ListeningState.LISTENING
        stt.startListening()
    }
    fun stop(){
        _listeningState.value = ListeningState.FINISHED_LISTENING
        stt.stopListening()
    }

    fun reset(){
        _answerState.value = AnswerState.NOT_ANSWERED
        _listeningState.value = ListeningState.NOT_LISTENING
        stt.resetText()
    }
    fun playSound(word: String){
        soundPlayer.stop()
        soundPlayer.enqueue("$dir/$word.mp3")
    }
    val lessonState: StateFlow<LessonUiState> =
        data.lesson(lessonId)
            .map {
                soundManager.downloadBatch(lessonId, it.words.map { word -> Pair(word.text+".mp3", enmp3(word.text+".mp3"))})
                soundManager.downloadBatch(lessonId, it.words.map { word -> Pair(word.translation+".mp3", tamp3(word.translation+".mp3"))})
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
enum class ListeningState {
    LISTENING,
    NOT_LISTENING,
    FINISHED_LISTENING
}
//enum class AnswerState {
//    NOT_ANSWERED,
//    WRONG,
//    CORRECT,
//    ANSWERING_WRONG,
//    ANSWERING_CORRECT,
//    ANSWERED_WRONG,
//    ANSWERED_CORRECT,
//}