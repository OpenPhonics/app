/*
 * Copyright 2020 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openphonics.backend.di

import android.app.Application
import androidx.work.WorkManager
import com.openphonics.backend.connectivity.ConnectivityObserverImpl
import com.openphonics.backend.utils.connectivityManager
import com.openphonics.core.connectivity.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideConnectivityObserver(application: Application): ConnectivityObserver {
        return ConnectivityObserverImpl(application.connectivityManager)
    }

    @Singleton
    @Provides
    fun provideWorkManager(application: Application): WorkManager {
        return WorkManager.getInstance(application)
    }
}
