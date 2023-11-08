package com.openphonics.repository

import javax.inject.Inject

class LocalLanguageRepository @Inject constructor(
    private val languageDao: LanguageDao
) : NotyNoteRepository {