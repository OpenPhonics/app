package com.advaitevdant.data.repository

import com.advaitevdant.data.util.suspendRunCatching
import com.advaitevdant.session.SessionManager
import javax.inject.Inject

interface AuthRepository {
    val isLoggedIn: Boolean
    suspend fun login(name: String): Boolean

    fun logout()
    suspend fun signup(name: String): Boolean
}

class AuthRepositoryImpl @Inject constructor(
    private val session: SessionManager
) : AuthRepository {

    override val isLoggedIn: Boolean
        get() = session.getToken() != null

    override suspend fun login(name: String): Boolean = suspendRunCatching {
        if (name != "advait") throw Exception() else session.saveToken("token")
    }.isSuccess

    override fun logout() {
        session.saveToken(null)
    }

    override suspend fun signup(name: String): Boolean = suspendRunCatching {
        if (name != "advait") throw Exception() else session.saveToken("token")
    }.isSuccess

}