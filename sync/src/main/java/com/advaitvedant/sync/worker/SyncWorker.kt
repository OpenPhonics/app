package com.advaitvedant.sync.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.advaitvedant.data.repository.FirstSkillRepository
import com.advaitvedant.data.utils.Synchronizer
import com.advaitvedant.datastore.AppPreferencesDataSource
import com.advaitvedant.datastore.ChangeListVersions
import com.advaitvedant.sync.utils.syncForegroundInfo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    private val prefManager: AppPreferencesDataSource,
    private val skillRepository: FirstSkillRepository,
    @Assisted private val appContext : Context,
    @Assisted params : WorkerParameters
) : CoroutineWorker(appContext,params), Synchronizer {
    override suspend fun getForegroundInfo(): ForegroundInfo =
        appContext.syncForegroundInfo()
    override suspend fun doWork(): Result {
        val success = skillRepository.sync()
        if (success) return Result.success()
        return Result.failure()
    }

    override suspend fun getChangeListVersions(): ChangeListVersions = prefManager.getChangeListVersions()

    override suspend fun updateChangeListVersions(
        update: ChangeListVersions.() -> ChangeListVersions
    ) = prefManager.updateChangeListVersion(update)
    companion object {
        fun startUpSyncWork(): OneTimeWorkRequest {
            Log.d("SYNC STUFF", "starting build")
            return OneTimeWorkRequestBuilder<DelegatingWorker>()
//                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setConstraints(SyncConstraints)
                .setInputData(SyncWorker::class.delegatedData())
                .build()
        }
    }
}
val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()