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

package com.openphonics.core.task


import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Singleton

@Singleton
interface OpenPhonicsTaskManager {
    /**
     * Schedules a task for syncing notes.
     *
     * @return Unique work ID
     */
    fun getLanguages(native: String): UUID

    /**
     * Schedules a [NotyTask] task
     *
     * @return Unique work ID
     */
    fun getWords(language: Int): UUID

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
}
