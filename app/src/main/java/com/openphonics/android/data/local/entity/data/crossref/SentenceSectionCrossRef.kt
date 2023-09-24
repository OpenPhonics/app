package com.openphonics.android.data.local.entity.data.crossref

import androidx.room.Entity

@Entity(tableName = "sentence_section_cross_ref", primaryKeys = ["sectionId", "sentenceId"])
data class SentenceSectionCrossRef(
    val sectionId: Int,
    val sentenceId: Int
)