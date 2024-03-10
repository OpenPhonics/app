package com.advaitevdant.data.repository

import com.advaitvedant.datastore.OpPreferencesDataSource
import com.advaitvedant.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setDarkTheme(darkMode: Boolean)
}

class UserDataRepositoryImpl @Inject constructor(
    private val opPreferencesDataSource: OpPreferencesDataSource
) : UserDataRepository {
    override val userData: Flow<UserData>
        get() = opPreferencesDataSource.userData

    override suspend fun setDarkTheme(darkMode: Boolean) =
        opPreferencesDataSource.setDarkModePreference(darkMode)
}
