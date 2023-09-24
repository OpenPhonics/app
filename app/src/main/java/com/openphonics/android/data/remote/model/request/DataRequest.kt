package com.openphonics.android.data.remote.model.request



data class LanguageRequest(
    val nativeId: String,
    val languageId: String,
    val languageName: String,
    val flag: String,
)


data class UnitRequest(
    val title: String,
    val order: Int,
    val languageId: Int
)


data class SectionRequest(
    val title: String,
    val order: Int,
    val lessonCount: Int,
    val unitId: Int
)


data class SentenceRequest(
    val languageId: Int,
    val words: List<Int>
)


data class WordRequest(
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
    val languageId: Int
)


data class FlagRequest(
    val flag: String,
    val id: String
)


data class WordSectionRequest(
    val word: Int
)


data class SentenceSectionRequest(
    val sentence: Int
)


data class UpdateUnitRequest(
    val title: String,
    val order: Int
)


data class UpdateWordRequest(
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
)


data class UpdateSentenceRequest(
    val words: List<Int>
)


data class UpdateFlagRequest(
    val flag: String
)