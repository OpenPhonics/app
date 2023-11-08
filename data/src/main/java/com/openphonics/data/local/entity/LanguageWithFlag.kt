package com.openphonics.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LanguageWithFlag(
    @Embedded
    val flag: FlagEntity,
    @Relation
        (parentColumn = "id",
        entityColumn = "flag")
    val language: LanguageEntity
)