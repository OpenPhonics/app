package com.advaitvedant.database.utils

import androidx.room.Dao
import androidx.room.Query
import com.advaitvedant.database.model.EntityModel
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {

    fun all(): Flow<List<T>>
}