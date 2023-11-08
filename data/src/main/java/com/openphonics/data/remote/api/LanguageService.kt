package com.openphonics.data.remote.api

import com.openphonics.data.Constant.ALL
import com.openphonics.data.Constant.LANGUAGES
import com.openphonics.data.Constant.NATIVE
import com.openphonics.data.Constant.ID
import com.openphonics.data.remote.request.LanguageRequest
import com.openphonics.data.remote.request.UpdateLanguageRequest
import com.openphonics.data.remote.response.IntResponse
import com.openphonics.data.remote.response.LanguageResponse
import retrofit2.Response
import retrofit2.http.*

interface LanguageService {
    @GET("$LANGUAGES/$ALL/{$NATIVE}")
    suspend fun getAllLanguages(@Path(NATIVE) native: String): Response<LanguageResponse>
    @POST(LANGUAGES)
    suspend fun createLanguage(@Body request: LanguageRequest): Response<IntResponse>
    @GET("${LANGUAGES}/{$ID}")
    suspend fun getLanguage(@Path(ID) id: Int): Response<LanguageResponse>
    @PUT("${LANGUAGES}/{$ID}")
    suspend fun updateLanguage(
        @Path(ID) id: Int,
        @Body request: UpdateLanguageRequest
    ): Response<IntResponse>

    @DELETE("${LANGUAGES}/{$ID}")
    suspend fun deleteLanguage(@Path(ID) id: Int): Response<IntResponse>
}