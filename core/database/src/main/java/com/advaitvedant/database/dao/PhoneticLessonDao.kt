package com.advaitvedant.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.advaitvedant.database.model.PhoneticLessonEntity
import com.advaitvedant.database.model.PhoneticLessonSentenceCrossRef
import com.advaitvedant.database.model.PhoneticLessonWordCrossRef
import com.advaitvedant.database.model.PopulatedPhoneticLesson
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneticLessonDao {

    @Transaction
    @Query(
        value = """
            SELECT * FROM phonetic_lessons
            WHERE 
                CASE WHEN :useIds
                    THEN id IN (:filterIds)
                    ELSE 1
                END
    """,
    )
    fun getPhoneticLessons(
        useIds: Boolean = false,
        filterIds: Set<Int> = emptySet(),
    ): Flow<List<PopulatedPhoneticLesson>>

    @Upsert
    suspend fun upsertPhoneticLessons(phoneticLessonEntities: List<PhoneticLessonEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreWordCrossRefEntities(
        phoneticLessonWordCrossRef: PhoneticLessonWordCrossRef,
    )
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreSentenceCrossRefEntities(
        phoneticLessonSentenceCrossRef: PhoneticLessonSentenceCrossRef,
    )
    @Transaction
    @Query(
        value = """
            DELETE FROM phonetic_lessons
            WHERE id in (:ids)
        """,
    )
    suspend fun deletePhoneticLessons(ids: Set<Int>)
}