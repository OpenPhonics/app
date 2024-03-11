package com.advaitevdant.data.repository

import com.advaitvedant.model.Lesson
import com.advaitvedant.model.LessonState
import com.advaitvedant.model.LessonWithData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DataRepository {
    fun lessons(): Flow<List<Lesson>>

    fun updateProgress(lessonId: Int)

    fun lesson(id: Int): Flow<LessonWithData>
}
class DataRepositoryImpl @Inject constructor() : DataRepository {
    override fun lessons(): Flow<List<Lesson>> = flow {
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
        emit(lessons)
    }

    override fun updateProgress(lessonId: Int) {
        TODO("Not yet implemented")
    }

    override fun lesson(id: Int): Flow<LessonWithData> {
        TODO("Not yet implemented")
    }


}