package com.advaitvedant.audioplayer.di

import android.content.Context
import com.advaitvedant.audioplayer.SoundManager
import com.advaitvedant.audioplayer.SoundPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SoundModule {
    @Provides
    @Singleton
    fun provideSoundPlayer(@ApplicationContext application: Context): SoundPlayer {
        return SoundPlayer(application)
    }
    @Provides
    @Singleton
    fun provideSoundManager(@ApplicationContext application: Context): SoundManager {
        return SoundManager(application)
    }
}