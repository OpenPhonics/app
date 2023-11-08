package com.openphonics.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "languages",
    foreignKeys = [ForeignKey(
        entity = FlagEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("flag"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class LanguageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val languageId: String,
    val nativeId: String,
    val languageName: String,
    val flag: String
)