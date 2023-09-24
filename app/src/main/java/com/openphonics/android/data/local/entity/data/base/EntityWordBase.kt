package com.openphonics.android.data.local.entity.data.base

data class EntityWordBase(
    val language: Int,
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
    val wordId: Int
)
