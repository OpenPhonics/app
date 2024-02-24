package com.advaitvedant.data.utils

import android.util.Log
import com.advaitvedant.database.model.ProgressChangeListEntity
import com.advaitvedant.datastore.ChangeListVersions
import com.advaitvedant.network.model.ProgressChangeList
import com.advaitvedant.network.model.StaticChangeList
import junit.runner.Version.id
import kotlin.coroutines.cancellation.CancellationException

/**
 * Interface marker for a class that manages synchronization between local data and a remote
 * source for a [Syncable].
 */
interface Synchronizer {
    fun filterLatest(source: Map<Int, ProgressChangeList>, comparator: Map<Int, ProgressChangeList>): List<ProgressChangeList> =
        source.filter { !comparator.containsKey(it.key) || comparator[it.key]!!.updated < it.value.updated }
            .values.toList()
    suspend fun getChangeListVersions(): ChangeListVersions

    suspend fun updateChangeListVersions(update: ChangeListVersions.() -> ChangeListVersions)

    /**
     * Syntactic sugar to call [Syncable.syncWith] while omitting the synchronizer argument
     */
    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)
}

/**
 * Interface marker for a class that is synchronized with a remote source. Syncing must not be
 * performed concurrently and it is the [Synchronizer]'s responsibility to ensure this.
 */
interface Syncable {
    /**
     * Synchronizes the local database backing the repository with the network.
     * Returns if the sync was successful or not.
     */
    suspend fun syncWith(synchronizer: Synchronizer): Boolean


}

//fun ProgressChangeListEntity.asNetwork() = ProgressChangeList(
//    id = this.id,
//    updated = this.updated,
//    isDelete = this.isDelete
//)



/**
 * Attempts [block], returning a successful [Result] if it succeeds, otherwise a [Result.Failure]
 * taking care not to break structured concurrency
 */
suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Log.i(
        "suspendRunCatching",
        "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result",
        exception,
    )
    Result.failure(exception)
}
fun ProgressChangeListEntity.asNetwork() = ProgressChangeList(
    id = this.id,
    updated = this.updated,
    isDelete = this.isDelete
)

/**
 * Utility function for syncing a repository with the network.
 * [versionReader] Reads the current version of the model that needs to be synced
 * [changeListFetcher] Fetches the change list for the model
 * [versionUpdater] Updates the [ChangeListVersions] after a successful sync
 * [modelDeleter] Deletes models by consuming the ids of the models that have been deleted.
 * [modelUpdater] Updates models by consuming the ids of the models that have changed.
 *
 * Note that the blocks defined above are never run concurrently, and the [Synchronizer]
 * implementation must guarantee this.
 */
suspend fun Synchronizer.changeListSync(
    versionReader: (ChangeListVersions) -> Int,
    changeListFetcher: suspend (Int) -> List<StaticChangeList>,
    versionUpdater: ChangeListVersions.(Int) -> ChangeListVersions,
    modelDeleter: suspend (List<Int>) -> Unit,
    modelUpdater: suspend (List<Int>) -> Unit,
) = suspendRunCatching {
    // Fetch the change list since last sync (akin to a git fetch)

    val currentVersion = versionReader(getChangeListVersions())
    Log.d("SYNC STUFF", currentVersion.toString())
    val changeList: List<StaticChangeList> = changeListFetcher(currentVersion)
    Log.d("SYNC STUFF", changeList.toString())
    if (changeList.isEmpty()) return@suspendRunCatching true

    //Splits the model into deleted and updated models
    val (deleted, updated) = changeList.partition(StaticChangeList::isDelete)
    Log.d("SYNC STUFF", deleted.toString())

    // Delete models that have been deleted server-side
    modelDeleter(deleted.map(StaticChangeList::id))
    Log.d("SYNC STUFF", "CON!")
    // Using the change list, pull down and save the changes (akin to a git pull)
    modelUpdater(updated.map(StaticChangeList::id))
    Log.d("SYNC STUFF", updated.toString())
    // Update the last synced version (akin to updating local git HEAD)
    val latestVersion = changeList.last().version
    updateChangeListVersions {
        versionUpdater(latestVersion)
    }
    Log.d("SYNC STUFF", "DONE")
}.isSuccess

suspend fun Synchronizer.changeListSync(
    remoteProgressFetcher: suspend () -> List<ProgressChangeList>,
    localProgressFetcher: suspend () -> List<ProgressChangeList>,
    modelRemoteDeleter: suspend (List<Int>) -> Unit,
    modelRemoteUpdater: suspend (List<Int>) -> Unit,
    modelLocalDeleter: suspend (List<Int>) -> Unit,
    modelLocalUpdater: suspend (List<Int>) -> Unit,
) = suspendRunCatching {
    val remote = remoteProgressFetcher().associateBy(ProgressChangeList::id)
    val local = localProgressFetcher().associateBy(ProgressChangeList::id)

    val (deleteLocal, updateLocal) = filterLatest(
        source = remote, comparator = local
    ).partition(ProgressChangeList::isDelete)

    val (deleteRemote, updateRemote) = filterLatest(
        source = local, comparator = remote
    ).partition(ProgressChangeList::isDelete)

    modelLocalDeleter(deleteLocal.map(ProgressChangeList::id))
    modelLocalUpdater(updateLocal.map(ProgressChangeList::id))

    modelRemoteDeleter(deleteRemote.map(ProgressChangeList::id))
    modelRemoteUpdater(updateRemote.map(ProgressChangeList::id))
}.isSuccess
