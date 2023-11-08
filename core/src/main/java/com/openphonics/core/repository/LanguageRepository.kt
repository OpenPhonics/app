package com.openphonics.core.repository

import com.openphonics.core.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    fun getAllLanguages(): Flow<Either<List<Language>>>
}