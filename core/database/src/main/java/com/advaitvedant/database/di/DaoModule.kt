package com.advaitvedant.database.di

import com.advaitvedant.database.AppDatabase
import com.advaitvedant.database.dao.SkillDao
import com.advaitvedant.database.dao.SkillDaoImpl
import com.advaitvedant.database.model.SkillEntity
import com.advaitvedant.database.utils.SyncStaticDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesSkillDao(
        database: AppDatabase,
    ): SkillDao = database.skillDao()

//    @Provides
//    fun bindsSkillDao(
//        skillDao: SkillDao,
//    ): SyncStaticDao<SkillEntity> = SkillDaoImpl(skillDao)
//    @Provides
//    fun providesWordDao(
//        database: AppDatabase,
//    ): WordDao = database.wordDao()
//    @Provides
//    fun providesSentenceDao(
//        database: AppDatabase,
//    ): SentenceDao = database.sentenceDao()
}