package com.openphonics.android.core.model.data

import com.openphonics.android.data.local.entity.data.EntitySectionWithLessons
import com.openphonics.android.data.local.entity.data.base.EntitySectionBase
import com.openphonics.android.data.local.entity.data.base.EntitySentenceBase


data class Section (
    val title: String,
    val order: Int,
    val lessonCount: Int,
    val words: List<Word>,
    val sentences: List<Sentence>,
    val hasData: Boolean,
    val unit: Int,
    val id: Int
) {
    companion object {
        fun fromEntityBase(entity: EntitySectionBase): Section = Section(
            entity.title,
            entity.order,
            entity.lessonCount,
            emptyList(),
            emptyList(),
            false,
            entity.unit,
            entity.sectionId
        )
        fun fromEntityLesson(entity: EntitySectionWithLessons): Section = Section(
            entity.section.title,
            entity.section.order,
            entity.section.lessonCount,
            entity.words.map { Word.fromEntityBase(it)},
            entity.sentences.map { Sentence.fromEntityBase(it)},
            true,
            entity.section.unit,
            entity.section.sectionId
        )
    }
}
