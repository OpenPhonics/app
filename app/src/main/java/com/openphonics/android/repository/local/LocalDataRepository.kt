package com.openphonics.android.repository.local

import com.openphonics.android.core.model.data.Language
import com.openphonics.android.core.model.data.Sentence
import com.openphonics.android.core.model.data.Word
import com.openphonics.android.core.repository.Either
import com.openphonics.android.core.repository.OpenPhonicsDataRepository
import com.openphonics.android.data.local.dao.DataDao
import com.openphonics.android.data.local.entity.data.EntityLanguageWithLessons
import com.openphonics.android.data.local.entity.data.base.*
import com.openphonics.android.data.local.entity.data.crossref.SentenceSectionCrossRef
import com.openphonics.android.data.local.entity.data.crossref.WordSectionCrossRef
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class LocalDataRepository @Inject constructor(
    private val dataDao: DataDao
) : OpenPhonicsDataRepository {
    override fun getAllLanguages(native: String, depth: Int): Flow<Either<List<Language>>> {
        return when (depth){
            Language.LANGUAGE ->
                dataDao.getAllLanguagesBase(native)
                    .filterNotNull()
                    .map { languages ->
                        languages.map {
                            Language.fromEntityBase(it)
                        }
                    }
            Language.LANGUAGE_WITH_UNITS ->
                dataDao.getAllLanguagesWithUnits(native)
                    .filterNotNull()
                    .map { languages ->
                        languages.map {
                            Language.fromEntityWithUnits(it)
                        }
                    }
            Language.LANGUAGE_WITH_UNITS_WITH_SECTIONS ->
                dataDao.getAllLanguagesWithSections(native)
                    .filterNotNull()
                    .map { languages ->
                        languages.map {
                            Language.fromEntityWithSections(it)
                        }
                    }
            Language.LANGUAGE_WITH_UNITS_WITH_SECTION_WITH_LESSON_DATA ->
                dataDao.getAllLanguagesWithLessons(native)
                    .filterNotNull()
                    .map { languages ->
                        languages.map {
                            Language.fromEntityWithLessons(it)
                        }
                    }
            else -> emptyFlow()
        }.transform { languages -> emit(Either.success(languages)) }
            .catch { emit(Either.success(emptyList())) }
    }
    override fun getLanguageById(languageId: Int, depth: Int): Flow<Language> {
        return when (depth){
            Language.LANGUAGE ->
                dataDao.getLanguageBaseById(languageId)
                    .filterNotNull()
                    .map { Language.fromEntityBase(it)}
            Language.LANGUAGE_WITH_UNITS ->
                dataDao.getLanguageWithUnitsById(languageId)
                    .filterNotNull()
                    .map { Language.fromEntityWithUnits(it) }
            Language.LANGUAGE_WITH_UNITS_WITH_SECTIONS ->
                dataDao.getLanguageWithSectionsById(languageId)
                    .filterNotNull()
                    .map {Language.fromEntityWithSections(it) }
            Language.LANGUAGE_WITH_UNITS_WITH_SECTION_WITH_LESSON_DATA ->
                dataDao.getLanguageWithLessonsById(languageId)
                    .filterNotNull()
                    .map { Language.fromEntityWithLessons(it)}
            else -> emptyFlow()
        }
    }

    override suspend fun addLanguage(native: String, language: String, name: String, flag: String): Either<Int>  = runCatching {
        val tempLanguageId = OpenPhonicsDataRepository.generateTemporaryId()
        dataDao.addLanguage(
            EntityLanguageBase(
                nativeId = native,
                languageId = language,
                languageName = name,
                flag = flag,
                id = tempLanguageId
            )
        )
        Either.success(tempLanguageId)
    }.getOrDefault(Either.error("Unable to create a new language"))

    override suspend fun syncLanguage(language: Language, words: List<Word>, sentences: List<Sentence>) {
        dataDao.addLanguage(EntityLanguageBase(language.nativeId, language.languageId, language.languageName, language.flag, language.id))
        words.map {word ->
            EntityWordBase(word.language, word.phonic, word.sound ,word.translatedSound, word.translatedWord, word.word, word.id)
        }.let {dataDao.addWords(it) }
        sentences.map {sentence->
            EntitySentenceBase(sentence.language, sentence.sentence, sentence.id)
        }.let {dataDao.addSentences(it)}
        language.units.map { unit ->
            unit.sections.map { section ->
                EntitySectionBase(
                    section.title,
                    section.order,
                    section.lessonCount,
                    section.unit,
                    section.id
                )
            }.let {  dataDao.addSections(it)}
            EntityUnitBase(unit.language, unit.title, unit.order, unit.id)
        }.let {dataDao.addUnits(it)}

        language.units.map {unit->
            unit.sections.map { section ->
                EntitySectionBase(
                    section.title,
                    section.order,
                    section.lessonCount,
                    section.unit,
                    section.id
                )
            }.let {  dataDao.addSections(it)}
        }
        language.units.map {unit->
            unit.sections.map {section ->
                dataDao.removeAllWordsFromSection(section.id)
                dataDao.removeAllSentencesFromSection(section.id)
                section.words.map { word ->
                    dataDao.addWordToSection(WordSectionCrossRef(section.id, word.id))
                }
                section.sentences.forEach { sentence ->
                    dataDao.addSentenceToSection(SentenceSectionCrossRef(section.id, sentence.id))
                }
            }
        }
    }

    override suspend fun updateLanguage(
        languageId: Int,
        native: String,
        language: String,
        name: String,
        flag: String
    ): Either<Int> = runCatching {
        dataDao.updateLanguageById(languageId, native, language, name, flag)
        Either.success(languageId)
    }.getOrDefault(Either.error("Unable to update a language"))

    override suspend fun deleteLanguage(languageId: Int): Either<Int> = runCatching {
        dataDao.deleteLanguageById(languageId)
        Either.success(languageId)
    }.getOrDefault(Either.error("Unable to delete a language"))

    override suspend fun updateLanguageId(oldLanguageId: Int, newLanguageId: Int)  =
        dataDao.updatedLanguageId(oldLanguageId, newLanguageId)

}