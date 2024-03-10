package com.advaitvedant.model

data class Lesson(
    val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
    val progress: Float? = 0f,
)

data class LessonWithData(
    val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
    val progress: Float? = 0f,
    val words: List<Word> = emptyList(),
    val sentences: List<Sentence> = emptyList()
)


enum class LessonState {
    LOCKED,
    PROGRESS,
    COMPLETED
}