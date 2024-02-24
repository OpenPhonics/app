package com.advaitvedant.data.repository

import com.advaitvedant.data.utils.suspendRunCatching
import com.advaitvedant.network.OpNetworkDataSource
import com.advaitvedant.network.request.AuthRequest
import com.advaitvedant.sharedpref.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    suspend fun authenticate(): Boolean
    suspend fun login(username: String): Boolean
    suspend fun signup(username: String): Boolean
    fun logout()
}

class FirstAuthRepository @Inject constructor(
    private val source: OpNetworkDataSource,
    private val sessionManager: SessionManager
) : AuthRepository {
    override val isLoggedIn: Flow<Boolean> = sessionManager.getTokenFlow().map { authenticate() }

    override fun logout() {
        sessionManager.saveToken(null)
    }
    override suspend fun authenticate() = suspendRunCatching {
        source.authenticate()
    }.isSuccess
    override suspend fun login(username: String): Boolean = suspendRunCatching {
        sessionManager.saveToken(source.login(AuthRequest(username)))
    }.isSuccess

    override suspend fun signup(username: String) = suspendRunCatching {
        sessionManager.saveToken(source.signup(AuthRequest(username)))
    }.isSuccess
}