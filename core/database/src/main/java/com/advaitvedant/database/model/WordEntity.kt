package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("word_change_lists")
data class WordChangeListEntity(
    @PrimaryKey
    override val id: Int = 1,
    @ColumnInfo(name = "is_delete")
    override val isDelete: Boolean = false,
    override val updated: Long = -1
) : ChangeListEntity


@Entity("words")
data class WordEntity(
    @PrimaryKey
    override val id: Int = -1,
    val phonetic: String = "",
    val text: String = "",
    val translation: String = "",
    val isLearned: Boolean = false
) : EntityModel