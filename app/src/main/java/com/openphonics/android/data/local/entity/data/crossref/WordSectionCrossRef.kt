package com.openphonics.android.data.local.entity.data.crossref

import androidx.room.Entity

@Entity(tableName = "word_section_cross_ref", primaryKeys = ["sectionId", "wordId"])
data class WordSectionCrossRef(
    val sectionId: Int,
    val wordId: Int
)