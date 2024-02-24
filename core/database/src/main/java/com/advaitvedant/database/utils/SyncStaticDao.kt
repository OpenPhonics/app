package com.advaitvedant.database.utils

import androidx.room.Dao
import androidx.room.Upsert
import com.advaitvedant.database.model.EntityModel

interface SyncStaticDao<T> : BaseDao<T> {
    suspend fun upsert(entities: List<T>)
    suspend fun delete(ids: Set<Int>)
}

