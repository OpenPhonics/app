package com.openphonics.data.remote.api

import com.openphonics.data.remote.response.*
import com.openphonics.data.remote.request.*
import com.openphonics.data.remote.Constant.FLAGS
import com.openphonics.data.remote.Constant.ID
import retrofit2.Response
import retrofit2.http.*

interface FlagService {
    @GET(FLAGS)
    suspend fun getAllFlags(): Response<FlagResponse>
    @POST(FLAGS)
    suspend fun createFlag(@Body request: FlagRequest): Response<StrResponse>
    @GET("$FLAGS/{$ID}")
    suspend fun getFlag(@Path(ID) id: String): Response<FlagResponse>
    @PUT("$FLAGS/{$ID}")
    suspend fun updateFlag(
        @Path(ID) id: String,
        @Body request: UpdateFlagRequest
    ): Response<StrResponse>

    @DELETE("$FLAGS/{$ID}")
    suspend fun deleteFlag(@Path(ID) id: String): Response<StrResponse>
}