package com.openphonics.android.data.local.entity.data

import androidx.room.Embedded
import androidx.room.Relation
import com.openphonics.android.data.local.entity.data.base.EntityLanguageBase
import com.openphonics.android.data.local.entity.data.base.EntityUnitBase

data class EntityLanguageWithUnits(
    @Embedded val language: EntityLanguageBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "language"
    )
    val units: List<EntityUnitBase>,
)
data class EntityLanguageWithSections(
    @Embedded val language: EntityLanguageBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "language"
    )
    val units: List<EntityUnitWithSections>,
)
data class EntityLanguageWithLessons(
    @Embedded val language: EntityLanguageBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "language"
    )
    val units: List<EntityUnitWithLessons>,
)