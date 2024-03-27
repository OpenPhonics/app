package com.advaitvedant.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.advaitvedant.database.model.PopulatedSentence
import com.advaitvedant.database.model.SentenceEntity
import com.advaitvedant.database.model.SentenceWordCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface SentenceDao {
    @Transaction
    @Query(
        value = """
            SELECT * FROM sentences
            WHERE 
                CASE WHEN :useIds
                    THEN id IN (:filterIds)
                    ELSE 1
                END
    """,
    )
    fun getSentences(
        useIds: Boolean = false,
        filterIds: Set<Int> = emptySet(),
    ): Flow<List<PopulatedSentence>>

    @Upsert
    suspend fun upsertSentences(sentenceEntities: List<SentenceEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreWordCrossRefEntities(
        sentenceWordCrossRef: SentenceWordCrossRef,
    )
    @Transaction
    @Query(
        value = """
            DELETE FROM sentences
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteSentences(ids: Set<Int>)
}