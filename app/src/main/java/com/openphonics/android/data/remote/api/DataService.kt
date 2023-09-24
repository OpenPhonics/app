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

package com.openphonics.android.data.remote.api

import com.openphonics.android.data.remote.model.request.DepthRequest
import com.openphonics.android.data.remote.model.request.LanguageRequest
import com.openphonics.android.data.remote.model.response.IntIdResponse
import com.openphonics.android.data.remote.model.response.LanguageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DataService {

    @GET("/data/languages/all/{native}")
    suspend fun getAllLanguages(@Path("native") native: String, @Body depthRequest: DepthRequest): Response<LanguageResponse>

    @GET("/data/languages/{id}")
    suspend fun getLanguage(@Path("id") id: Int, @Body depthRequest: DepthRequest): Response<LanguageResponse>

    @POST("/data/languages/create")
    suspend fun addLanguage(@Body languageRequest: LanguageRequest): Response<IntIdResponse>

    @PUT("/data/languages/{languageId}")
    suspend fun updateLanguage(
        @Path("languageId") languageId: Int,
        @Body languageRequest: LanguageRequest
    ): Response<IntIdResponse>

    @DELETE("/data/languages/{languageId}")
    suspend fun deleteLanguage(@Path("languageId") languageId: Int): Response<IntIdResponse>

}
