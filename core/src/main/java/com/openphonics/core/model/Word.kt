package com.openphonics.core.model

data class Word(
    val language: Int,
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
    val id: Int
)