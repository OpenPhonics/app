package com.advaitvedant.network

import com.advaitvedant.network.model.NetworkModel
import com.advaitvedant.network.model.ProgressChangeList
import com.advaitvedant.network.model.SkillNetwork
import com.advaitvedant.network.model.StaticChangeList
import com.advaitvedant.network.request.AuthRequest
import retrofit2.Response

interface OpNetworkDataSource : SyncStaticService<SkillNetwork> {
    suspend fun login(user: AuthRequest): String
    suspend fun signup(user: AuthRequest): String
    suspend fun authenticate(): Boolean
}

interface SyncStaticService<T : NetworkModel> {
    suspend fun all(ids: Set<Int>? = null): List<T>
    suspend fun changeList(version: Int? = null): List<StaticChangeList>
}
interface SyncProgressService<T : NetworkModel> {
    suspend fun all(ids: Set<Int>? = null): List<T>
    suspend fun changeList(): List<ProgressChangeList>
    suspend fun update(models: List<T>): Boolean
    suspend fun delete(ids: Set<Int>): Boolean
}

