package com.openphonics.android.data.local.entity.data.base

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "languages")
data class EntityLanguageBase(
    @PrimaryKey(autoGenerate = false)
    val nativeId:String,
    val languageId: String,
    val languageName: String,
    val flag: String,
    val id: Int,
)
