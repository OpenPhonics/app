package com.advaitvedant.database.di

import com.advaitvedant.database.AppDatabase
import com.advaitvedant.database.dao.PhoneticLessonDao
import com.advaitvedant.database.dao.SentenceDao
import com.advaitvedant.database.dao.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesPhoneticLessonDao(
        database: AppDatabase,
    ): PhoneticLessonDao = database.phoneticLessonDao()

    @Provides
    fun providesWordDao(
        database: AppDatabase,
    ): WordDao = database.wordDao()

    @Provides
    fun providesSentenceDao(
        database: AppDatabase,
    ): SentenceDao = database.sentenceDao()
}