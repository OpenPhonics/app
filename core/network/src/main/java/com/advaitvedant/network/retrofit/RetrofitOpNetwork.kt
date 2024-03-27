package com.advaitvedant.network.retrofit

import com.advaitvedant.network.OpNetworkDataSource
import com.advaitvedant.network.interceptor.AuthInterceptor
import com.advaitvedant.network.model.ChangeListNetwork
import com.advaitvedant.network.model.PhoneticLessonDynamicNetwork
import com.advaitvedant.network.model.PhoneticLessonNetwork
import com.advaitvedant.network.model.SentenceNetwork
import com.advaitvedant.network.model.WordDynamicNetwork
import com.advaitvedant.network.model.WordNetwork
import com.advaitvedant.network.model.toDynamic
import com.advaitvedant.network.request.AuthRequest
import com.advaitvedant.network.response.BaseResponse
import com.advaitvedant.network.utils.Routes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


private interface OpServiceRetrofit {
    @POST(Routes.LOGIN)
    suspend fun login(@Body user: AuthRequest): BaseResponse<String>

    @POST(Routes.SIGNUP)
    suspend fun signup(@Body user: AuthRequest): BaseResponse<String>

    @GET(Routes.AUTH)
    suspend fun authenticate(): BaseResponse<Boolean>

    @GET(Routes.LESSONS)
    suspend fun lessons(@Query("id") ids: List<Int>?
    ): BaseResponse<List<PhoneticLessonNetwork>>

    @GET(Routes.WORDS)
    suspend fun words(@Query("id") ids: List<Int>?): BaseResponse<List<WordNetwork>>

    @GET(Routes.SENTENCES)
    suspend fun sentences(@Query("id") ids: List<Int>?): BaseResponse<List<SentenceNetwork>>

    @GET(Routes.LESSONS + "/" + Routes.CHANGE_LIST)
    suspend fun lessonsChangeList(@Query("after") after: Long?): BaseResponse<List<ChangeListNetwork>>

    @GET(Routes.WORDS + "/" + Routes.CHANGE_LIST)
    suspend fun wordsChangeList(@Query("after") after: Long?): BaseResponse<List<ChangeListNetwork>>

    @GET(Routes.SENTENCES + "/" + Routes.CHANGE_LIST)
    suspend fun sentencesChangeList(@Query("after") after: Long?): BaseResponse<List<ChangeListNetwork>>

    @PATCH(Routes.LESSONS)
    suspend fun updateLessons(@Body lessons: List<PhoneticLessonDynamicNetwork>): BaseResponse<Boolean>

    @PATCH(Routes.WORDS)
    suspend fun updateWords(@Body words: List<WordDynamicNetwork>): BaseResponse<Boolean>

    @DELETE(Routes.LESSONS)
    suspend fun deleteLessons(@Query("id") ids: List<Int>): BaseResponse<Boolean>

    @DELETE(Routes.WORDS)
    suspend fun deleteWords(@Query("id") ids: List<Int>): BaseResponse<Boolean>

}

@Singleton
class RetrofitOpNetwork @Inject constructor(
    authInterceptor: AuthInterceptor
) : OpNetworkDataSource {
    private val networkApi = Retrofit.Builder().baseUrl(Routes.BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(authInterceptor).build()).build()
        .create(OpServiceRetrofit::class.java)

    override suspend fun login(user: AuthRequest): String = networkApi.login(user).data

    override suspend fun signup(user: AuthRequest): String = networkApi.signup(user).data

    override suspend fun authenticate(): Boolean = networkApi.authenticate().data
    override suspend fun lessons(ids: List<Int>?): List<PhoneticLessonNetwork> =
        networkApi.lessons(ids).data

    override suspend fun lessonsChangeList(updatedAfter: Long?): List<ChangeListNetwork> =
        networkApi.lessonsChangeList(updatedAfter).data

    override suspend fun updateLessons(models: List<PhoneticLessonNetwork>): Boolean
        = networkApi.updateLessons(models.map { it.toDynamic() }).data

    override suspend fun deleteLessons(ids: List<Int>): Boolean
        = networkApi.deleteLessons(ids).data

    override suspend fun words(ids: List<Int>?): List<WordNetwork>
        = networkApi.words(ids).data

    override suspend fun wordsChangeList(updatedAfter: Long?): List<ChangeListNetwork>
        = networkApi.wordsChangeList(updatedAfter).data

    override suspend fun updateWords(models: List<WordNetwork>): Boolean
        = networkApi.updateWords(models.map { it.toDynamic() }).data

    override suspend fun deleteWords(ids: List<Int>): Boolean
        = networkApi.deleteWords(ids).data

    override suspend fun sentences(ids: List<Int>?): List<SentenceNetwork>
        = networkApi.sentences(ids).data

    override suspend fun sentencesChangeList(updatedAfter: Long?): List<ChangeListNetwork>
        = networkApi.sentencesChangeList(updatedAfter).data

}

//override suspend fun all(ids: List<Int>?): List<SkillNetwork> = ('a'..'z')
//    .map {
//        SkillNetwork(id = it.code, name = it.toString(), words = listOf(SkillWordNetwork(1, "hello",  "hɛɫoʊ", "", "வணக்கம்","")))
//    }.let { it ->
//        if (ids == null)
//            return it
//        else
//            return it.filter { it.id in ids }
//    }
//override suspend fun changeList(version: Int?): List<StaticChangeList>  = ('a'..'z').map { StaticChangeList(id = it.code, version = 3, it.code % 2 == 0)}