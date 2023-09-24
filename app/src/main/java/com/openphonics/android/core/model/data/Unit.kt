package com.openphonics.android.core.model.data

import com.openphonics.android.data.local.entity.data.EntityUnitWithLessons
import com.openphonics.android.data.local.entity.data.EntityUnitWithSections
import com.openphonics.android.data.local.entity.data.base.EntityUnitBase

data class Unit(
    val title: String,
    val order: Int,
    val sections: List<Section>,
    val hasData: Boolean,
    val language: Int,
    val id: Int
){
    companion object {
        const val UNIT: Int = 0
        const val UNIT_WITH_SECTIONS: Int = 1
        const val UNIT_WITH_SECTIONS_WITH_LESSON_DATA: Int = 2
        fun fromEntityBase(entity: EntityUnitBase) = Unit(
            entity.title,
            entity.order,
            emptyList(),
            false,
            entity.language,
            entity.unitId
        )
        fun fromEntityWithSections(entity: EntityUnitWithSections) = Unit(
            entity.unit.title,
            entity.unit.order,
            entity.sections.map { Section.fromEntityBase(it) },
            true,
            entity.unit.language,
            entity.unit.unitId
        )
        fun fromEntityWithLessons(entity: EntityUnitWithLessons) = Unit(
            entity.unit.title,
            entity.unit.order,
            entity.sections.map { Section.fromEntityLesson(it) },
            true,
            entity.unit.language,
            entity.unit.unitId
        )
    }
}