package com.openphonics.android.data.local.entity.data

import androidx.room.Embedded
import androidx.room.Relation
import com.openphonics.android.data.local.entity.data.base.EntityLanguageBase
import com.openphonics.android.data.local.entity.data.base.EntitySectionBase
import com.openphonics.android.data.local.entity.data.base.EntityUnitBase

data class EntityUnitWithSections(
    @Embedded val unit: EntityUnitBase,
    @Relation(
        parentColumn = "unitId",
        entityColumn = "unit"
    )
    val sections: List<EntitySectionBase>,
)

data class EntityUnitWithLessons(
    @Embedded val unit: EntityUnitBase,
    @Relation(
        parentColumn = "unitId",
        entityColumn = "unit"
    )
    val sections: List<EntitySectionWithLessons>,
)