package com.openphonics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openphonics.data.local.entity.FlagEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface FlagDao {
//    @Query("SELECT * FROM flags WHERE id = :id")
//    fun getFlagById(id: String): Flow<FlagEntity?>
//
//    @Query("SELECT * FROM flags ORDER BY id ASC")
//    fun getAllFlags(): Flow<List<FlagEntity>>

    @Insert
    suspend fun addFlag(flag: FlagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFlags(flags: List<FlagEntity>)

//    @Query("UPDATE flags SET flag = :flag WHERE id = :id")
//    suspend fun updateFlagById(id: String, flag: String)
//
//    @Query("DELETE FROM flags WHERE id = :id")
//    suspend fun deleteFlagById(id: String)

    @Query("DELETE FROM flags")
    suspend fun deleteAllFlags()

//    @Query("UPDATE flags SET id = :newFlagId WHERE id = :oldFlagId")
//    fun updateFlagId(oldFlagId: String, newFlagId: String)
}