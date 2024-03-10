package com.advaitevdant.data.di

import com.advaitevdant.data.repository.AuthRepository
import com.advaitevdant.data.repository.AuthRepositoryImpl
import com.advaitevdant.data.repository.DataRepository
import com.advaitevdant.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsAuth(
        auth: AuthRepositoryImpl
    ) : AuthRepository

    @Binds
    fun bindsData(
        data: DataRepositoryImpl
    ) : DataRepository
}