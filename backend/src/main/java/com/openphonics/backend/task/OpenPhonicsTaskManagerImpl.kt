package com.openphonics.backend.task

import androidx.work.WorkManager
import com.openphonics.core.task.OpenPhonicsTaskManager
import com.openphonics.core.task.TaskState
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenPhonicsTaskManagerImpl @Inject constructor(
    private val workManager: WorkManager
) : OpenPhonicsTaskManager {
    override fun getLanguages(native: String): UUID {
        TODO("Not yet implemented")
    }

    override fun getWords(language: Int): UUID {
        TODO("Not yet implemented")
    }

    override fun observeTask(taskId: UUID): Flow<TaskState> {
        TODO("Not yet implemented")
    }

    override fun abortAllTasks() {
        TODO("Not yet implemented")
    }
}