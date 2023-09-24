package com.openphonics.android.data.remote.model.request

data class LanguageProgressRequest(
    val languageId: Int
)

data class XPRequest(
    val xp: Int
)

data class StreakRequest(
    val continueStreak: Boolean
)

data class SectionProgressRequest(
    val currentLesson: Int = 0,
    val isLegendary: Boolean,
    val learnedWords: List<Int>
)