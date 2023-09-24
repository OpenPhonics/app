package com.openphonics.android.core.model.progress

data class LanguageProgress(
    val progressId: String,
    val started: Long,
    val updated: Long,
    val streak: Int,
    val xp: Int,
    val nativeId: String,
    val languageId: String,
    val languageName: String,
    val flag: String,
    val units: List<UnitProgress>,
    val hasData: Boolean,
    val id: String
)
