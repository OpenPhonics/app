package com.openphonics.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openphonics.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
//    @Query("SELECT * FROM words WHERE id = :id")
//    fun getWordById(id: String): Flow<WordEntity?>

    @Query("SELECT * FROM words WHERE language = :language ORDER BY word ASC")
    fun getAllWords(language: Int): Flow<List<WordEntity>>

    @Insert
    suspend fun addWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWords(words: List<WordEntity>)

//    @Query("UPDATE words SET phonic = :phonic, sound = :sound, translatedSound = :translatedSound, translatedWord = :translatedWord, word = :word WHERE id = :id")
//    suspend fun updateWordById(id: Int, phonic: String, sound: String, translatedSound: String, translatedWord: String, word: String)

    @Query("DELETE FROM words WHERE language = :language")
    suspend fun deleteWordByLanguage(language: Int)

    @Query("DELETE FROM words")
    suspend fun deleteAllWords()

//    @Query("UPDATE words SET id = :newWordId WHERE id = :oldWordId")
//    fun updateWordId(oldWordId: Int, newWordId: Int)
}