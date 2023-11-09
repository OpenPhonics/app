package com.openphonics.repository

import com.openphonics.core.model.Flag
import com.openphonics.core.model.Language
import com.openphonics.core.model.Word
import com.openphonics.core.repository.Either
import com.openphonics.core.repository.OpenPhonicsRepository
import com.openphonics.data.remote.api.LanguageService
import com.openphonics.data.remote.api.WordService
import com.openphonics.data.remote.response.State
import com.openphonics.data.remote.utils.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenPhonicsRemoteRepository @Inject constructor(
    private val languageService: LanguageService,
    private val wordService: WordService
) : OpenPhonicsRepository {
    override fun getAllLanguages(native: String): Flow<Either<List<Language>>>  = flow {
        val languagesResponse = languageService.getAllLanguages(native).getResponse()

        val state = when (languagesResponse.status) {
            State.SUCCESS -> Either.success(languagesResponse.language)
            else -> Either.error(languagesResponse.message)
        }

        emit(state)
    }.catch { emit(Either.error("Can't sync latest languages")) }

    override fun getLanguage(id: Int): Flow<Language> {
        TODO("Not yet implemented")
    }

    override fun getAllWords(language: Int): Flow<Either<List<Word>>> = flow {
        val wordsResponse = wordService.getAllWords(language).getResponse()

        val state = when (wordsResponse.status) {
            State.SUCCESS -> Either.success(wordsResponse.word)
            else -> Either.error(wordsResponse.message)
        }

        emit(state)
    }.catch { emit(Either.error("Can't sync latest words")) }

    override suspend fun addFlags(flags: List<Flag>) {
        TODO("Not yet implemented")
    }

    override suspend fun addWords(words: List<Word>) {
        TODO("Not yet implemented")
    }

    override suspend fun addLanguages(languages: List<Language>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLanguage(id: Int): Either<Int> {
        TODO("Not yet implemented")
    }

}