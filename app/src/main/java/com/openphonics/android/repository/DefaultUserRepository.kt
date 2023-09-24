package com.openphonics.android.repository

import com.openphonics.android.core.model.AuthCredential
import com.openphonics.android.core.repository.Either
import com.openphonics.android.core.repository.UserRepository
import com.openphonics.android.data.remote.api.AuthService
import com.openphonics.android.data.remote.model.request.LoginRequest
import com.openphonics.android.data.remote.model.request.UserSignUpRequest
import com.openphonics.android.data.remote.model.response.State
import com.openphonics.android.data.remote.util.getResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject internal constructor(
    private val authService: AuthService
) : UserRepository {

    override suspend fun addUser(
        name: String,
        classCode: String,
        native: String,
        language: Int
    ): Either<AuthCredential> {
        return runCatching {
            val authResponse = authService.register(UserSignUpRequest(name, classCode, native ,language)).getResponse()

            when (authResponse.status) {
                State.SUCCESS -> Either.success(AuthCredential(authResponse.token!!))
                else -> Either.error(authResponse.message)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

    override suspend fun getUserByNameAndClassCode(
        name: String,
        classCode: String
    ): Either<AuthCredential> {
        return runCatching {
            val authResponse = authService.login(LoginRequest(name, classCode)).getResponse()

            when (authResponse.status) {
                State.SUCCESS -> Either.success(AuthCredential(authResponse.token!!))
                else -> Either.error(authResponse.message)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

}
