package com.openphonics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openphonics.data.local.entity.LanguageEntity
import com.openphonics.data.local.entity.LanguageWithFlag
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {

    @Query("SELECT * FROM languages WHERE id = :id")
    fun getLanguageWithFlagById(id: Int): Flow<LanguageWithFlag?>

    @Query("SELECT * FROM languages ORDER BY languageName ASC")
    fun getAllLanguagesWithFlags(): Flow<List<LanguageWithFlag>>

    @Insert
    suspend fun addLanguage(language: LanguageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLanguages(languages: List<LanguageEntity>)

//    @Query("UPDATE languages SET languageId = :languageId, nativeId = :nativeId, languageName = :languageName, flag = :flag WHERE id = :id")
//    suspend fun updateLanguageById(id: Int, languageId: String, nativeId: String, languageName: String, flag: String)

    @Query("DELETE FROM languages WHERE id = :id")
    suspend fun deleteLanguageById(id: Int)

    @Query("DELETE FROM languages")
    suspend fun deleteAllLanguages()

//    @Query("UPDATE languages SET id = :newLanguageId WHERE id = :oldLanguageId")
//    fun updateLanguageId(oldLanguageId: Int, newLanguageId: Int)
}