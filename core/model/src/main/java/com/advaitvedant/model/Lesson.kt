package com.advaitvedant.model

data class Lesson(
    val id: Int,
    val num: Int,
    val phonetic: String,
    val state: LessonState,
    val progress: Float? = 0f
)


enum class LessonState {
    LOCKED,
    PROGRESS,
    COMPLETED
}