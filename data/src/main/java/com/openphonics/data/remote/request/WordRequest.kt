package com.openphonics.data.remote.request


data class WordRequest(
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
    val language: Int
)
data class UpdateWordRequest(
    val phonic: String? = null,
    val sound: String? = null,
    val translatedSound: String? = null,
    val translatedWord: String? = null,
    val word: String? = null
)