package com.openphonics.android.data.local.entity.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.openphonics.android.data.local.entity.data.base.EntitySectionBase
import com.openphonics.android.data.local.entity.data.base.EntitySentenceBase
import com.openphonics.android.data.local.entity.data.base.EntityUnitBase
import com.openphonics.android.data.local.entity.data.base.EntityWordBase
import com.openphonics.android.data.local.entity.data.crossref.SentenceSectionCrossRef
import com.openphonics.android.data.local.entity.data.crossref.WordSectionCrossRef

data class EntitySectionWithLessons(
    @Embedded val section: EntitySectionBase,
    @Relation(
        parentColumn = "sectionId",
        entityColumn = "wordId",
        associateBy = Junction(WordSectionCrossRef::class)
    )
    val words: List<EntityWordBase>,
    @Relation(
        parentColumn = "sectionId",
        entityColumn = "sentenceId",
        associateBy = Junction(SentenceSectionCrossRef::class)
    )
    val sentences: List<EntitySentenceBase>,
)
