package com.openphonics.android.backend.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.openphonics.android.backend.di.LocalRepository
import com.openphonics.android.backend.di.RemoteRepository
import com.openphonics.android.core.model.data.Language
import com.openphonics.android.core.repository.Either
import com.openphonics.android.core.repository.OpenPhonicsDataRepository
import com.openphonics.android.core.task.OpenPhonicsTaskManager
import com.openphonics.android.core.task.TaskState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.util.*

@HiltWorker
class OpenPhonicsSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @RemoteRepository private val remoteDataRepository: OpenPhonicsDataRepository,
    @LocalRepository private val localDataRepository: OpenPhonicsDataRepository,
    private val openPhonicsTaskManager: OpenPhonicsTaskManager
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return syncData()
    }

    private suspend fun syncData(): Result {
        return try {
            // Fetches all notes from remote.
            // If task of any note is still pending, skip it.
            val notes = fetchRemoteData().filter { language -> shouldReplaceData(language.id) }

            // Add/Replace notes locally.
            localDataRepository.addLanguages(notes)

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    private suspend fun fetchRemoteData(): List<Language> {
        return when (val response = remoteDataRepository.getCurrentLanguage(native, depth = Language.LANGUAGE_WITH_UNITS_WITH_SECTION_WITH_LESSON_DATA).first()) {
            is Either.Success -> response.data
            is Either.Error -> throw Exception(response.message)
        }
    }

    private fun shouldReplaceData(languageId: Int): Boolean {
        val taskId = openPhonicsTaskManager.getTaskIdFromLanguageId(languageId).toUUID()
        val state = openPhonicsTaskManager.getTaskState(taskId)
        return (state == null || state != TaskState.SCHEDULED)
    }

    private fun Int.toUUID(): UUID = UUID.fromString(this.toString())
}