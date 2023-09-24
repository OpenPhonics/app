package com.openphonics.android.core.model.user



data class Classroom(
    val className: String,
    val students: List<Student>,
    val id: String
)