package com.advaitvedant.database.utils

import androidx.room.Dao
import androidx.room.Query
import com.advaitvedant.database.model.EntityModel
import com.advaitvedant.database.model.EntityType
import com.advaitvedant.database.model.ProgressChangeListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncProgressDao<T : EntityModel> : SyncStaticDao<T> {
    fun changeList(): Flow<List<ProgressChangeListEntity>>
    fun get(ids: Set<Int>): Flow<List<T>>
    @Query("SELECT * FROM progress_change_list WHERE entity = :entity")
    fun changeList(entity: EntityType): Flow<List<ProgressChangeListEntity>>
}