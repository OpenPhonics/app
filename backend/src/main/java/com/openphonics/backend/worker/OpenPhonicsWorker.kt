package com.openphonics.backend.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.openphonics.backend.di.LocalRepository
import com.openphonics.backend.di.RemoteRepository
import com.openphonics.core.model.Language
import com.openphonics.core.repository.Either
import com.openphonics.core.repository.OpenPhonicsRepository
import com.openphonics.core.task.OpenPhonicsTaskManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.util.*

@HiltWorker
class OpenPhonicsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @RemoteRepository private val remote: OpenPhonicsRepository,
    @LocalRepository private val local: OpenPhonicsRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val nativeId = getNativeId()
        return getLanguages(nativeId)
    }

    private suspend fun getLanguages(nativeId: String): Result {
        return try {
            val languages = fetchRemoteLanguages(nativeId)
            local.addLanguages(languages)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    private suspend fun fetchRemoteLanguages(native: String): List<Language> {
        return when (val response = remote.getAllLanguages(native).first()) {
            is Either.Success -> response.data
            is Either.Error -> throw Exception(response.message)
        }
    }
    private fun getNativeId(): String = inputData.getString(KEY_NATIVE_ID)
        ?: throw IllegalStateException("$KEY_NATIVE_ID should be provided as input data.")
    companion object {
        const val KEY_NATIVE_ID = "native_id"
    }
}