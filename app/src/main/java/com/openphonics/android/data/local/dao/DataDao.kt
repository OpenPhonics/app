package com.openphonics.android.data.local.dao

import androidx.room.*
import com.openphonics.android.data.local.entity.data.EntityLanguageWithLessons
import com.openphonics.android.data.local.entity.data.EntityLanguageWithSections
import com.openphonics.android.data.local.entity.data.EntityLanguageWithUnits
import com.openphonics.android.data.local.entity.data.base.*
import com.openphonics.android.data.local.entity.data.crossref.SentenceSectionCrossRef
import com.openphonics.android.data.local.entity.data.crossref.WordSectionCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {

    @Transaction
    @Query("SELECT * FROM languages WHERE id = :languageId")
    fun getLanguageBaseById(languageId: Int): Flow<EntityLanguageBase>

    @Transaction
    @Query("SELECT * FROM languages WHERE id = :languageId")
    fun getLanguageWithUnitsById(languageId: Int): Flow<EntityLanguageWithUnits>

    @Transaction
    @Query("SELECT * FROM languages WHERE id = :languageId")
    fun getLanguageWithSectionsById(languageId: Int): Flow<EntityLanguageWithSections>

    @Transaction
    @Query("SELECT * FROM languages WHERE id = :languageId")
    fun getLanguageWithLessonsById(languageId: Int): Flow<EntityLanguageWithLessons>

    @Transaction
    @Query("SELECT * FROM languages WHERE nativeId = :native")
    fun getAllLanguagesBase(native: String): Flow<List<EntityLanguageBase>>
    @Transaction
    @Query("SELECT * FROM languages WHERE nativeId = :native")
    fun getAllLanguagesWithUnits(native: String): Flow<List<EntityLanguageWithUnits>>
    @Transaction
    @Query("SELECT * FROM languages WHERE nativeId = :native")
    fun getAllLanguagesWithSections(native: String): Flow<List<EntityLanguageWithSections>>
    @Transaction
    @Query("SELECT * FROM languages WHERE nativeId = :native")
    fun getAllLanguagesWithLessons(native: String): Flow<List<EntityLanguageWithLessons>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLanguage(language: EntityLanguageBase)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUnits(language: List<EntityUnitBase>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSections(language: List<EntitySectionBase>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWords(language: List<EntityWordBase>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSentences(language: List<EntitySentenceBase>)

    suspend fun addWordToSection(crossRef: WordSectionCrossRef)
    suspend fun addSentenceToSection(crossRef: SentenceSectionCrossRef)

    @Query("DELETE FROM word_section_cross_ref WHERE sectionId = :sectionId")
    suspend fun removeAllWordsFromSection(sectionId: Int)

    @Query("DELETE FROM sentence_section_cross_ref WHERE sectionId = :sectionId")
    suspend fun removeAllSentencesFromSection(sectionId: Int)

    @Query("UPDATE languages SET nativeId = :native, languageId = :language, languageName = :name, flag = :flag WHERE id = :id")
    suspend fun updateLanguageById(id: Int, native: String, language: String, name: String, flag: String)

    @Query("UPDATE languages SET id = :newId WHERE id = :oldId")
    suspend fun updatedLanguageId(oldId: Int, newId: Int)

    @Query("DELETE FROM languages WHERE id = :languageId")
    suspend fun deleteLanguageById(languageId: Int)
}