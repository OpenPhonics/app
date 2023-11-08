package com.openphonics.core.model

data class Language (
    val nativeId: String,
    val languageId: String,
    val languageName: String,
    val flag: Flag,
    val id: Int,
)