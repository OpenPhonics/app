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

package com.openphonics.android.backend.di

import com.openphonics.android.core.repository.OpenPhonicsDataRepository
import com.openphonics.android.core.repository.UserRepository
import com.openphonics.android.repository.DefaultUserRepository
import com.openphonics.android.repository.RemoteDataRepository
import com.openphonics.android.repository.local.LocalDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun openPhonicsAuthRepository(openPhonicsAuthRepository: DefaultUserRepository): UserRepository

    @Binds
    @LocalRepository
    fun openPhonicsLocalNoteRepository(localRepository: LocalDataRepository): OpenPhonicsDataRepository

    @Binds
    @RemoteRepository
    fun openPhonicsRemoteNoteRepository(remoteRepository: RemoteDataRepository): OpenPhonicsDataRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalRepository

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository
