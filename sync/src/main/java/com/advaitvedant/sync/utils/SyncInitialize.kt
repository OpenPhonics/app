package com.advaitvedant.sync.utils

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.advaitvedant.sync.manager.SyncWorkName
import com.advaitvedant.sync.worker.SyncWorker

object Sync {
    fun initialize(context: Context) {
        Log.d("SYNC STUFF", "started init")
        WorkManager.getInstance(context).apply {
            // Run sync on app startup and ensure only one sync worker runs at any time
            enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork(),
            )
        }
        Log.d("SYNC STUFF", "completed init")
    }
}