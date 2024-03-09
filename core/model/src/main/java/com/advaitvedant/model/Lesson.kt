package com.advaitvedant.model

data class Lesson(
    val id: Int,
    val num: Int,
    val phonetic: String,
    val state: LessonState,
)


enum class LessonState {
    LOCKED,
    PROGRESS,
    COMPLETED
}