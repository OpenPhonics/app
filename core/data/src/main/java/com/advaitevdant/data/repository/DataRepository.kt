package com.advaitevdant.data.repository

import com.advaitvedant.model.PhoneticLesson
import com.advaitvedant.model.LessonState
import com.advaitvedant.model.PhoneticLessonData
import com.advaitvedant.model.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DataRepository {
    fun lessons(): Flow<List<PhoneticLesson>>

    fun lesson(id: Int): Flow<PhoneticLessonData>
}
class DataRepositoryImpl @Inject constructor() : DataRepository {
    override fun lessons(): Flow<List<PhoneticLesson>> = flow {
        val phoneticLessons = mutableListOf<PhoneticLesson>()
        val phoneticAlphabet = ('a'..'z').toList()
        repeat(100) { index ->
            val num = index + 1
            val phonetic = phoneticAlphabet[index % phoneticAlphabet.size].toString()
            val state = when {
                index < 6 -> LessonState.COMPLETED
                index == 6 -> LessonState.PROGRESS
                else -> LessonState.LOCKED
            }
            phoneticLessons.add(PhoneticLesson(num, num, phonetic, state))
        }
        emit(phoneticLessons)
    }
    override fun lesson(id: Int): Flow<PhoneticLessonData>  = flow {
        emit(PhoneticLessonData(
            id = id,
            words = listOf(Word(
                id = 0,
                phonetic = "",
                text = "hello",
                translation = "வணக்கம்",
                isLearned = false
            ),
                Word(
                    id = 0,
                    phonetic = "",
                    text = "hello",
                    translation = "வணக்கம்",
                    isLearned = false
                ),
                Word(
                    id = 0,
                    phonetic = "",
                    text = "hello",
                    translation = "வணக்கம்",
                    isLearned = false
                ),
                Word(
                    id = 0,
                    phonetic = "",
                    text = "hello",
                    translation = "வணக்கம்",
                    isLearned = false
                ),
                Word(
                    id = 0,
                    phonetic = "",
                    text = "hello",
                    translation = "வணக்கம்",
                    isLearned = false
                )
            )
        ))
    }


}