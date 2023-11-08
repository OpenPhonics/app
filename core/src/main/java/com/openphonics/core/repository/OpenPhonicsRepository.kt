package com.openphonics.core.repository

import com.openphonics.core.model.*
import kotlinx.coroutines.flow.Flow

interface OpenPhonicsRepository {
    fun getAllLanguages(native: String): Flow<Either<List<Language>>>
    fun getLanguage(id: Int): Flow<Language>
    fun getAllWords(language: Int): Flow<Either<List<Word>>>
    suspend fun addFlags(flags: List<Flag>)
    suspend fun addWords(words: List<Word>)
    suspend fun addLanguages(languages: List<Language>)
    suspend fun deleteLanguage(id: Int): Either<Int>
}