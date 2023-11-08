package com.openphonics.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "words",
    foreignKeys = [ForeignKey(
        entity = LanguageEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("language"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val phonic: String,
    val sound: String,
    val translatedWord: String,
    val translatedSound: String,
    val word: String,
    val language: Int
)