package com.advaitvedant.datastore

import androidx.datastore.core.DataStore
import com.advaitvedant.datastore.proto.UserPreferences
import com.advaitvedant.datastore.proto.copy
import com.advaitvedant.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OpPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(
                it.isDarkTheme
            )
        }

    suspend fun setDarkModePreference(useDarkMode: Boolean) {
        userPreferences.updateData {
            it.copy { this.isDarkTheme = useDarkMode }
        }
    }
}
