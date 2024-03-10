package com.advaitevdant.session.di

import android.app.Application
import com.advaitevdant.session.OpSharedPreferencesFactory
import com.advaitevdant.session.SessionManager
import com.advaitevdant.session.SessionManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSessionManager(application: Application): SessionManager {
        return SessionManagerImpl(OpSharedPreferencesFactory.sessionPreferences(application))
    }
}