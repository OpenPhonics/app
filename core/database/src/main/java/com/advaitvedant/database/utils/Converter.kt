package com.advaitvedant.database.utils

import androidx.room.TypeConverter
import com.advaitvedant.database.model.EntityType

class EntityConverter {
    @TypeConverter
    fun fromEntity(entity: EntityType): String {
        return entity.name
    }

    @TypeConverter
    fun toStatus(entity: String): EntityType {
        return EntityType.valueOf(entity)
    }
}