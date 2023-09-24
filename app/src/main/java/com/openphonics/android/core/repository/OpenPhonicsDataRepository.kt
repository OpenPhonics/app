/*
 * Copyright 2020 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openphonics.android.core.repository

import com.openphonics.android.core.model.data.Language
import com.openphonics.android.core.model.data.Sentence
import com.openphonics.android.core.model.data.Word
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton
import kotlin.random.Random

/**
 * Repository for notes.
 */
@Singleton
interface OpenPhonicsDataRepository {



    /**
     * Returns all languages.
     */
    fun getAllLanguages(native: String, depth: Int): Flow<Either<List<Language>>>

    /**
     * Returns a language
     *
     * @param languageId A language ID.
     */
    fun getLanguageById(languageId: Int, depth: Int): Flow<Language>

    /**
     * creates a language
     * @param native the native ID
     * @param language the language ID
     * @param name the language name
     * @param flag the language flag
     */
    suspend fun addLanguage(
        native: String,
        language: String,
        name: String,
        flag: String
    ): Either<Int>

    /**
     * Adds a list of notes. Replaces notes if already exists
     */
    suspend fun syncLanguage(language: Language, words: List<Word>, sentences: List<Sentence>)

    /**
     * Updates a new language having ID [languageId]
     *
     * @param native the native ID
     * @param language the language ID
     * @param name the language name
     * @param flag the language flag
     */
    suspend fun updateLanguage(
        languageId: Int,
        native: String,
        language: String,
        name: String,
        flag: String,
    ): Either<Int>

    /**
     * Deletes a new language having ID [languageId]
     */
    suspend fun deleteLanguage(languageId: Int): Either<Int>

    /**
     * Updates ID of a note
     */
    suspend fun updateLanguageId(oldLanguageId: Int, newLanguageId: Int)

    companion object {
        fun generateTemporaryId() = -Random.nextInt(1, 100000)
        fun isTemporary(id: Int) = id < 0
    }
}
