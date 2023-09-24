package com.openphonics.android.core.model.data

import com.openphonics.android.data.local.entity.data.base.EntityWordBase

data class Word(
    val language: Int,
    val phonic: String,
    val sound: String,
    val translatedSound: String,
    val translatedWord: String,
    val word: String,
    val id: Int
){
    companion object {
        fun fromEntityBase(entity: EntityWordBase): Word = Word(entity.language, entity.phonic, entity.sound, entity.translatedSound, entity.translatedWord, entity.word, entity.wordId)
    }
}
