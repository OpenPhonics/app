package com.advaitvedant.model

data class PhoneticLesson(
    override val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
) : Model

data class PhoneticLessonData(
    override val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
    val words: List<Word> = emptyList(),
    val sentences: List<Sentence> = emptyList()
) : Model


enum class LessonState {
    LOCKED,
    PROGRESS,
    COMPLETED
}