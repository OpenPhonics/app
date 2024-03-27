package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity("sentence_change_lists")
data class SentenceChangeListEntity(
    @PrimaryKey
    override val id: Int = 1,
    @ColumnInfo(name = "is_delete")
    override val isDelete: Boolean = false,
    override val updated: Long = -1
) : ChangeListEntity


@Entity("sentences")
data class SentenceEntity(
    @PrimaryKey
    override val id: Int = -1,
) : EntityModel

data class PopulatedSentence(
    @Embedded
    val entity: SentenceEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = SentenceWordCrossRef::class,
            parentColumn = "sentence_id",
            entityColumn = "word_id"
        )
    )
    val words: List<WordEntity>,
)