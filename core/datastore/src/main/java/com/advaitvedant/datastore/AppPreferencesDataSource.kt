package com.advaitvedant.datastore

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AppPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<AppPreferences>,
) {
    @RequiresApi(Build.VERSION_CODES.P)
    val appData = userPreferences.data

    suspend fun getChangeListVersions() = userPreferences.data
        .map {
            ChangeListVersions(
                skillVersion = it.skillChangeListVersion
            )
        }
        .firstOrNull() ?: ChangeListVersions()

    suspend fun updateChangeListVersion(update: ChangeListVersions.() -> ChangeListVersions) {
        try {
            userPreferences.updateData { currentPreferences ->
                val updatedChangeListVersions = update(
                    ChangeListVersions(
                        skillVersion = currentPreferences.skillChangeListVersion,
                    ),
                )

                currentPreferences.copy {
                    skillChangeListVersion = updatedChangeListVersions.skillVersion
                }
            }
        } catch (ioException: IOException) {
            Log.e("AppPreferences", "Failed to update user preferences", ioException)
        }
    }

}