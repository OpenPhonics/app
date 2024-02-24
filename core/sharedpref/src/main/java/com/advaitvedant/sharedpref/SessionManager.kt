package com.advaitvedant.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

@Singleton
interface SessionManager {

    fun saveToken(token: String?)

    fun getToken(): String?
    fun getTokenFlow(): StateFlow<String?>
}
class SessionManagerImpl(private val sharedPreferences: SharedPreferences) : SessionManager {
    private val authTokenFlow = MutableStateFlow(getToken())
    override fun saveToken(token: String?) {
        sharedPreferences.edit(commit = true) {
            putString(KEY_TOKEN, token)
        }
        authTokenFlow.value = token
    }

    override fun getTokenFlow(): StateFlow<String?> = authTokenFlow.asStateFlow()
    override fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)

    companion object {
        private const val KEY_TOKEN = "auth_token"
    }
}

object OpSharedPreferencesFactory {
    private const val FILE_NAME_SESSION_PREF = "auth_shared_pref"

    fun sessionPreferences(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            FILE_NAME_SESSION_PREF,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}