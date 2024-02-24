package com.advaitvedant.sharedpref.di

import android.app.Application
import com.advaitvedant.sharedpref.OpSharedPreferencesFactory
import com.advaitvedant.sharedpref.SessionManager
import com.advaitvedant.sharedpref.SessionManagerImpl
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