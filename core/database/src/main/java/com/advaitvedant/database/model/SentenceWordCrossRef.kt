package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sentence_word_cross_ref",
    primaryKeys = ["sentence_id", "word_id"],
    foreignKeys = [
        ForeignKey(
            entity = SentenceEntity::class,
            parentColumns = ["id"],
            childColumns = ["sentence_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["word_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["sentence_id"]),
        Index(value = ["word_id"]),
    ],
)
data class SentenceWordCrossRef(
    @ColumnInfo(name = "sentence_id")
    val sentenceId: Int,
    @ColumnInfo(name = "word_id")
    val wordId: Int,
)
