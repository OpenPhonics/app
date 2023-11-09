package com.openphonics.repository

import com.openphonics.core.model.Flag
import com.openphonics.core.model.Language
import com.openphonics.core.model.Word
import com.openphonics.core.repository.Either
import com.openphonics.core.repository.OpenPhonicsRepository
import com.openphonics.data.local.dao.FlagDao
import com.openphonics.data.local.dao.LanguageDao
import com.openphonics.data.local.dao.WordDao
import com.openphonics.data.local.entity.FlagEntity
import com.openphonics.data.local.entity.LanguageEntity
import com.openphonics.data.local.entity.WordEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OpenPhonicsLocalRepository @Inject constructor(
    private val languageDao: LanguageDao,
    private val wordDao: WordDao,
    private val flagDao: FlagDao
) : OpenPhonicsRepository {
    override fun getAllLanguages(native: String): Flow<Either<List<Language>>> = languageDao.getAllLanguagesWithFlags(native)
        .map { languages -> languages.map { Language(it.language.nativeId, it.language.languageId, it.language.languageName, Flag(it.flag.flag, it.flag.id), it.language.id) } }
        .transform { languages -> emit(Either.success(languages)) }
        .catch { emit(Either.success(emptyList())) }

    override fun getLanguage(id: Int): Flow<Language> = languageDao.getLanguageWithFlagById(id)
        .filterNotNull()
        .map { Language(it.language.nativeId, it.language.languageId, it.language.languageName, Flag(it.flag.flag, it.flag.id), it.language.id) }

    override fun getAllWords(language: Int): Flow<Either<List<Word>>> = wordDao.getAllWords(language)
        .map { words -> words.map { Word(it.language, it.phonic, it.sound, it.translatedSound, it.translatedWord, it.word, it.id) } }
        .transform { words -> emit(Either.success(words)) }
        .catch { emit(Either.success(emptyList())) }
    override suspend fun addFlags(flags: List<Flag>) = flags.map {
        FlagEntity(it.id, it.flag)
    }.let {
        flagDao.addFlags(it)
    }
    override suspend fun addWords(words: List<Word>) = words.map {
        WordEntity(it.id, it.phonic, it.sound, it.translatedWord, it.translatedSound, it.word, it.language)
    }.let {
        wordDao.addWords(it)
    }
    override suspend fun addLanguages(languages: List<Language>) = languages.map {
        LanguageEntity(it.id, it.languageId, it.nativeId, it.languageName, it.flag.id)
    }.let {
        languageDao.addLanguages(it)
    }
    override suspend fun deleteLanguage(id: Int): Either<Int> = runCatching {
        languageDao.deleteLanguageById(id)
        Either.success(id)
    }.getOrDefault(Either.error("Unable to delete a language"))
}