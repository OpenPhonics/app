package com.advaitvedant.network

import com.advaitvedant.network.model.NetworkChangeList
import com.advaitvedant.network.model.PhoneticLessonNetwork
import com.advaitvedant.network.model.SentenceNetwork
import com.advaitvedant.network.model.WordNetwork
import com.advaitvedant.network.request.AuthRequest

interface OpNetworkDataSource : AuthDataSource, PhoneticLessonDataSource, WordDataSource,
    SentenceDataSource

interface AuthDataSource {
    suspend fun login(user: AuthRequest): String
    suspend fun signup(user: AuthRequest): String
    suspend fun authenticate(): Boolean
}

interface PhoneticLessonDataSource {
    suspend fun lessons(ids: List<Int>? = null): List<PhoneticLessonNetwork>
    suspend fun lessonsChangeList(updatedAfter: Long? = null): List<NetworkChangeList>
    suspend fun updateLessons(models: List<PhoneticLessonNetwork>): Boolean
    suspend fun deleteLessons(ids: List<Int>): Boolean
}

interface WordDataSource {
    suspend fun words(ids: List<Int>? = null): List<WordNetwork>
    suspend fun wordsChangeList(updatedAfter: Long? = null): List<NetworkChangeList>
    suspend fun updateWords(models: List<WordNetwork>): Boolean
    suspend fun deleteWords(ids: List<Int>): Boolean
}

interface SentenceDataSource {
    suspend fun sentences(ids: List<Int>? = null): List<SentenceNetwork>
    suspend fun sentencesChangeList(updatedAfter: Long? = null): List<NetworkChangeList>
}