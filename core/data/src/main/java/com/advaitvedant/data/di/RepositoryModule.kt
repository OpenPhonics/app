package com.advaitvedant.data.di

import com.advaitvedant.data.repository.AuthRepository
import com.advaitvedant.data.repository.FirstAuthRepository
import com.advaitvedant.data.repository.FirstSkillRepository
import com.advaitvedant.data.repository.SkillRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(
        authRepository: FirstAuthRepository,
    ): AuthRepository

    @Binds
    fun bindsSkillRepository(
        skillRepository: FirstSkillRepository,
    ): SkillRepository
}