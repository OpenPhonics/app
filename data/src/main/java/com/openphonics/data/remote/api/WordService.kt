package com.openphonics.data.remote.api

import com.openphonics.data.remote.Constant.ALL
import com.openphonics.data.remote.Constant.ID
import com.openphonics.data.remote.Constant.LANGUAGE
import com.openphonics.data.remote.Constant.WORDS
import com.openphonics.data.remote.request.WordRequest
import com.openphonics.data.remote.request.UpdateWordRequest
import com.openphonics.data.remote.response.IntResponse
import com.openphonics.data.remote.response.WordResponse
import retrofit2.Response
import retrofit2.http.*

interface WordService {
    @GET("${WORDS}/${ALL}/{${LANGUAGE}}")
    suspend fun getAllWords(@Path(LANGUAGE) language: Int): Response<WordResponse>
    @POST(WORDS)
    suspend fun createWord(@Body request: WordRequest): Response<IntResponse>
    @GET("${WORDS}/{${ID}}")
    suspend fun getWord(@Path(ID) id: Int): Response<WordResponse>
    @PUT("${WORDS}/{${ID}}")
    suspend fun updateWord(
        @Path(ID) id: Int,
        @Body request: UpdateWordRequest
    ): Response<IntResponse>

    @DELETE("${WORDS}/{${ID}}")
    suspend fun deleteWord(@Path(ID) id: Int): Response<IntResponse>
}