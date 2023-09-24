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

import com.openphonics.android.core.utils.moshi
import com.openphonics.android.data.remote.Constant
import com.openphonics.android.data.remote.api.AuthService
import com.openphonics.android.data.remote.api.DataService
import com.openphonics.android.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

    @Provides
    fun provideDataService(authInterceptor: AuthInterceptor): DataService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(DataService::class.java)
    }

    @Provides
    fun provideAuthService(): AuthService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.build())
            .build()
            .create(AuthService::class.java)
    }
}
