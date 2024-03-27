package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "phonetic_lesson_word_cross_ref",
    primaryKeys = ["phonetic_lesson_id", "word_id"],
    foreignKeys = [
        ForeignKey(
            entity = PhoneticLessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["phonetic_lesson_id"],
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
        Index(value = ["phonetic_lesson_id"]),
        Index(value = ["topic_id"]),
    ],
)
data class PhoneticLessonWordCrossRef(
    @ColumnInfo(name = "phonetic_lesson_id")
    val phoneticLessonId: Int,
    @ColumnInfo(name = "word_id")
    val wordId: Int,
)


@Entity(
    tableName = "phonetic_lesson_sentence_cross_ref",
    primaryKeys = ["phonetic_lesson_id", "sentence_id"],
    foreignKeys = [
        ForeignKey(
            entity = PhoneticLessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["phonetic_lesson_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = SentenceEntity::class,
            parentColumns = ["id"],
            childColumns = ["sentence_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["phonetic_lesson_id"]),
        Index(value = ["sentence_id"]),
    ],
)
data class PhoneticLessonSentenceCrossRef(
    @ColumnInfo(name = "phonetic_lesson_id")
    val phoneticLessonId: Int,
    @ColumnInfo(name = "sentence_id")
    val sentenceId: Int,
)
