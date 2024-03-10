package com.advaitvedant.speechtotext.di

import android.content.Context
import com.advaitvedant.speechtotext.SpeechToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SttModule {

    @Provides
    @Singleton
    fun provideSpeechToTextParser(@ApplicationContext application: Context): SpeechToTextParser {
        return SpeechToTextParser(application)
    }
}