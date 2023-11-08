package com.openphonics.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LanguageWithWords(
    @Embedded
    val language: LanguageEntity,
    @Relation
        (parentColumn = "id",
        entityColumn = "language")
    val words: List<WordEntity>
)