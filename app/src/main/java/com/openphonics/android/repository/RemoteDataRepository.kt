package com.openphonics.android.repository

import com.openphonics.android.core.model.data.Language
import com.openphonics.android.core.model.data.Sentence
import com.openphonics.android.core.model.data.Word
import com.openphonics.android.core.repository.Either
import com.openphonics.android.core.repository.OpenPhonicsDataRepository
import com.openphonics.android.data.remote.api.DataService
import com.openphonics.android.data.remote.model.request.DepthRequest
import com.openphonics.android.data.remote.model.request.LanguageRequest
import com.openphonics.android.data.remote.model.response.State
import com.openphonics.android.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
    private val dataService: DataService
) : OpenPhonicsDataRepository {
    override fun getAllLanguages(native: String, depth: Int): Flow<Either<List<Language>>> = flow {
        val dataResponse = dataService.getAllLanguages(native, DepthRequest(depth)).getResponse()

        val state = when (dataResponse.status) {
            State.SUCCESS -> Either.success(dataResponse.language)
            else -> Either.error(dataResponse.message)
        }
        emit(state)
    }.catch { emit(Either.error("Can't sync latest data")) }

    override fun getLanguageById(languageId: Int, depth: Int): Flow<Language> = flow {
        emit(dataService.getLanguage(languageId, DepthRequest(depth)).getResponse().language.firstOrNull())
    }.filterNotNull()

    override suspend fun addLanguage(native: String, language: String, name: String, flag: String): Either<Int> {
        return runCatching {
            val dataResponse = dataService.addLanguage(LanguageRequest(native, language, name, flag)).getResponse()

            when (dataResponse.status) {
                State.SUCCESS -> Either.success(dataResponse.id!!)
                else -> Either.error(dataResponse.message)
            }
        }.getOrElse {
            it.printStackTrace()
            (Either.error("Something went wrong!"))
        }
    }

    override suspend fun syncLanguage(language: Language, words: List<Word>, sentences: List<Sentence>) {}

    override suspend fun updateLanguage(
        languageId: Int,
        native: String,
        language: String,
        name: String,
        flag: String
    ): Either<Int> {
        return runCatching {
            val dataResponse = dataService.updateLanguage(
                languageId,
                LanguageRequest(native, language, name, flag)
            ).getResponse()

            when (dataResponse.status) {
                State.SUCCESS -> Either.success(dataResponse.id!!)
                else -> Either.error(dataResponse.message)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

    override suspend fun deleteLanguage(languageId: Int): Either<Int> {
        return runCatching {
            val dataResponse = dataService.deleteLanguage(languageId).getResponse()

            when (dataResponse.status) {
                State.SUCCESS -> Either.success(dataResponse.id!!)
                else -> Either.error(dataResponse.message)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

    override suspend fun updateLanguageId(oldLanguageId: Int, newLanguageId: Int) {}

}