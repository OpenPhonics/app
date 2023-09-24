package com.openphonics.android.core.model

import com.openphonics.android.core.utils.encodeFlag

class DataTask private constructor(val  id: Int, val action: TaskAction) {
    companion object {
        fun createFlag(id: String) = DataTask(encodeFlag(id), TaskAction.CREATE_FLAG)
        fun updateFlag(id: String) = DataTask(encodeFlag(id), TaskAction.UPDATE_FLAG)
        fun deleteFlag(id: String) = DataTask(encodeFlag(id), TaskAction.DELETE_FLAG)
        fun createLanguage(id: Int) = DataTask(id, TaskAction.CREATE_LANGUAGE)
        fun updateLanguage(id: Int) = DataTask(id, TaskAction.UPDATE_LANGUAGE)
        fun deleteLanguage(id: Int) = DataTask(id, TaskAction.DELETE_LANGUAGE)
        fun createUnit(id: Int) = DataTask(id, TaskAction.CREATE_UNIT)
        fun updateUnit(id: Int) = DataTask(id, TaskAction.UPDATE_UNIT)
        fun deleteUnit(id: Int) = DataTask(id, TaskAction.DELETE_UNIT)
        fun createSection(id: Int) = DataTask(id, TaskAction.CREATE_SECTION)
        fun updateSection(id: Int) = DataTask(id, TaskAction.UPDATE_SECTION)
        fun deleteSection(id: Int) = DataTask(id, TaskAction.DELETE_SECTION)
        fun createWord(id: Int) = DataTask(id, TaskAction.CREATE_WORD)
        fun updateWord(id: Int) = DataTask(id, TaskAction.UPDATE_WORD)
        fun deleteWord(id: Int) = DataTask(id, TaskAction.DELETE_WORD)
        fun createSentence(id: Int) = DataTask(id, TaskAction.CREATE_SENTENCE)
        fun updateSentence(id: Int) = DataTask(id, TaskAction.UPDATE_SENTENCE)
        fun deleteSentence(id: Int) = DataTask(id, TaskAction.DELETE_SENTENCE)
        fun updateSectionWords(id: Int) = DataTask(id, TaskAction.UPDATE_SECTION_WORDS)
        fun updateSectionSentences(id: Int) = DataTask(id, TaskAction.UPDATE_SECTION_SENTENCES)
    }
}

class ProgressTask private constructor(val id: String, val action: TaskAction){
    companion object {
        fun createLanguageProgress(id: String) = ProgressTask(id, TaskAction.CREATE_LANGUAGE_PROGRESS)
        fun updateLanguageProgress(id: String) = ProgressTask(id, TaskAction.UPDATE_LANGUAGE_PROGRESS)
        fun deleteLanguageProgress(id: String) = ProgressTask(id, TaskAction.DELETE_LANGUAGE_PROGRESS)
        fun updatedStreakLanguageProgress(id: String) = ProgressTask(id, TaskAction.UPDATE_STREAK_LANGUAGE_PROGRESS)
        fun createUnitProgress(id: String) = ProgressTask(id, TaskAction.CREATE_UNIT_PROGRESS)
        fun updateUnitProgress(id: String) = ProgressTask(id, TaskAction.UPDATE_UNIT_PROGRESS)
        fun deleteUnitProgress(id: String) = ProgressTask(id, TaskAction.DELETE_UNIT_PROGRESS)
        fun createSectionProgress(id: String) = ProgressTask(id, TaskAction.CREATE_SECTION_PROGRESS)
        fun updateSectionProgress(id: String) = ProgressTask(id, TaskAction.UPDATE_SECTION_PROGRESS)
        fun deleteSectionProgress(id: String) = ProgressTask(id, TaskAction.DELETE_SECTION_PROGRESS)
        fun updateSectionProgressLearnedWords(id: String) = ProgressTask(id, TaskAction.UPDATE_LEARNED_WORDS)
    }
}

class UserTask private constructor(val id: String, val action: TaskAction){
    fun deleteClassroom(id: String) = UserTask(id, TaskAction.DELETE_CLASSROOM)
    fun deleteUser(id: String) = UserTask(id, TaskAction.DELETE_USER)
}

enum class TaskAction {
    CREATE_FLAG, UPDATE_FLAG, DELETE_FLAG,
    CREATE_LANGUAGE, UPDATE_LANGUAGE, DELETE_LANGUAGE,
    CREATE_UNIT, UPDATE_UNIT, DELETE_UNIT,
    CREATE_SECTION, UPDATE_SECTION, DELETE_SECTION,
    CREATE_WORD, UPDATE_WORD, DELETE_WORD,
    CREATE_SENTENCE, UPDATE_SENTENCE, DELETE_SENTENCE,
    UPDATE_SECTION_WORDS, UPDATE_SECTION_SENTENCES,
    CREATE_LANGUAGE_PROGRESS, UPDATE_LANGUAGE_PROGRESS, DELETE_LANGUAGE_PROGRESS, UPDATE_STREAK_LANGUAGE_PROGRESS,
    CREATE_UNIT_PROGRESS, UPDATE_UNIT_PROGRESS, DELETE_UNIT_PROGRESS,
    CREATE_SECTION_PROGRESS, UPDATE_SECTION_PROGRESS, DELETE_SECTION_PROGRESS, UPDATE_LEARNED_WORDS,
    DELETE_CLASSROOM, DELETE_USER
}
