package com.advaitvedant.network.retrofit

import com.advaitvedant.network.OpNetworkDataSource
import com.advaitvedant.network.R
import com.advaitvedant.network.interceptor.AuthInterceptor
import com.advaitvedant.network.model.SkillNetwork
import com.advaitvedant.network.model.SkillWordNetwork
import com.advaitvedant.network.model.StaticChangeList
import com.advaitvedant.network.utils.Routes
import com.advaitvedant.network.request.AuthRequest
import com.advaitvedant.network.response.BaseResponse
import com.advaitvedant.network.response.State
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton


private interface OpServiceRetrofit  {
    @POST(Routes.LOGIN)
    suspend fun login(@Body user: AuthRequest): BaseResponse<String>

    @POST(Routes.SIGNUP)
    suspend fun signup(@Body user: AuthRequest): BaseResponse<String>

    @GET(Routes.AUTH)
    suspend fun authenticate(): BaseResponse<String>

    @GET(Routes.SKILLS)
    suspend fun skills(): BaseResponse<List<SkillNetwork>>
}

@Singleton
class RetrofitOpNetwork @Inject constructor(
    authInterceptor: AuthInterceptor
) : OpNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(Routes.BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(authInterceptor).build())
        .build()
        .create(OpServiceRetrofit::class.java)
    override suspend fun login(user: AuthRequest): String
        = networkApi.login(user).data

    override suspend fun signup(user: AuthRequest): String
        = networkApi.signup(user).data

    override suspend fun authenticate(): Boolean
        = networkApi.authenticate().status == State.SUCCESS

    override suspend fun all(ids: Set<Int>?): List<SkillNetwork> = ('a'..'z')
        .map {
            SkillNetwork(id = it.code, name = it.toString(), words = listOf(SkillWordNetwork(1, "hello",  "hɛɫoʊ", "", "வணக்கம்","")))
        }.let { it ->
            if (ids == null)
                return it
            else
                return it.filter { it.id in ids }
        }
    override suspend fun changeList(version: Int?): List<StaticChangeList>  = ('a'..'z').map { StaticChangeList(id = it.code, version = 3, it.code % 2 == 0)}
}
