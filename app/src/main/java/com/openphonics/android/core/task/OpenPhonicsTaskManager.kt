/*
 * Copyright 2020 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openphonics.android.core.task

import com.openphonics.android.core.model.DataTask
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Singleton

@Singleton
interface OpenPhonicsTaskManager {
    /**
     * Schedules a task for syncing language data.
     *
     * @return Unique work ID
     */
    fun syncProgress(): UUID

    /**
     * Schedules a task for syncing user progress.
     *
     * @return Unique work ID
     */
    fun syncData(): UUID

    /**
     * Schedules a [DataTask] task
     *
     * @return Unique work ID
     */
    fun scheduleTask(dataTask: DataTask): UUID

    /**
     * Retrieves the state of a task
     *
     * @param taskId Unique work ID
     * @return Nullable (in case task not exists) task state
     */
    fun getTaskState(taskId: UUID): TaskState?

    /**
     * Returns Flowable task state of a specific task
     *
     * @param taskId Unique work ID
     * @return Flow of task state
     */
    fun observeTask(taskId: UUID): Flow<TaskState>

    /**
     * Aborts/Stops all scheduled (ongoing) tasks
     */
    fun abortAllTasks()

    /**
     * Generates task ID from note ID
     */
    fun getTaskIdFromLanguageId(languageId: Int) = languageId
}
