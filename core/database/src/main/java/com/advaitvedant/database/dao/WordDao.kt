package com.advaitvedant.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.advaitvedant.database.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Transaction
    @Query(
        value = """
            SELECT * FROM Words
            WHERE 
                CASE WHEN :useIds
                    THEN id IN (:filterIds)
                    ELSE 1
                END
    """,
    )
    fun getWords(
        useIds: Boolean = false,
        filterIds: Set<Int> = emptySet(),
    ): Flow<List<WordEntity>>

    @Upsert
    suspend fun upsertWords(wordEntities: List<WordEntity>)
    @Transaction
    @Query(
        value = """
            DELETE FROM words
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteWords(ids: Set<Int>)
}