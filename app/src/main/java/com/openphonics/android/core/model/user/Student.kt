package com.openphonics.android.core.model.user

import com.openphonics.android.core.model.progress.LanguageProgress

data class Student(
    val name: String,
    val classCode: String,
    val native: String,
    val progress: List<LanguageProgress>,
)