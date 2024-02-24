package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.advaitvedant.database.model.EntityModel
import com.advaitvedant.database.model.EntityType

@Entity("progress_change_list", primaryKeys = ["id", "entity"])
data class ProgressChangeListEntity(
    override val id: Int,
    val entity: EntityType,
    @ColumnInfo(name = "is_delete")
    val isDelete: Boolean,
    val updated: Long
) : EntityModel

