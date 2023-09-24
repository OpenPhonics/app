package com.openphonics.android.core.model.data

import com.openphonics.android.data.local.entity.data.EntityLanguageWithLessons
import com.openphonics.android.data.local.entity.data.EntityLanguageWithSections
import com.openphonics.android.data.local.entity.data.EntityLanguageWithUnits
import com.openphonics.android.data.local.entity.data.base.EntityLanguageBase


data class Language (
    val nativeId: String,
    val languageId: String,
    val languageName: String,
    val flag: String,
    val units: List<Unit>,
    val hasData: Boolean,
    val id: Int,
){
    companion object {
        const val LANGUAGE: Int = 0
        const val LANGUAGE_WITH_UNITS: Int = 1
        const val LANGUAGE_WITH_UNITS_WITH_SECTIONS: Int = 2
        const val LANGUAGE_WITH_UNITS_WITH_SECTION_WITH_LESSON_DATA: Int = 3
        fun fromEntityBase(entity: EntityLanguageBase) = Language(
            entity.nativeId,
            entity.languageId,
            entity.languageName,
            entity.flag,
            emptyList(),
            false,
            entity.id
        )

        fun fromEntityWithUnits(entity: EntityLanguageWithUnits) = Language(
            entity.language.nativeId,
            entity.language.languageId,
            entity.language.languageName,
            entity.language.flag,
            entity.units.map { Unit.fromEntityBase(it) },
            true,
            entity.language.id
        )

        fun fromEntityWithSections(entity: EntityLanguageWithSections) = Language(
            entity.language.nativeId,
            entity.language.languageId,
            entity.language.languageName,
            entity.language.flag,
            entity.units.map { Unit.fromEntityWithSections(it) },
            true,
            entity.language.id
        )

        fun fromEntityWithLessons(entity: EntityLanguageWithLessons) = Language(
            entity.language.nativeId,
            entity.language.languageId,
            entity.language.languageName,
            entity.language.flag,
            entity.units.map { Unit.fromEntityWithLessons(it) },
            true,
            entity.language.id
        )
    }
}