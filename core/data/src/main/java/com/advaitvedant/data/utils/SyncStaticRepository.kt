package com.advaitvedant.data.utils

import com.advaitvedant.database.model.EntityModel
import com.advaitvedant.database.utils.SyncStaticDao
import com.advaitvedant.datastore.ChangeListVersions
import com.advaitvedant.network.SyncStaticService
import com.advaitvedant.network.model.NetworkModel
import com.advaitvedant.network.model.StaticChangeList

abstract class SyncStaticRepository<LocalModel : EntityModel, RemoteModel : NetworkModel> : Syncable {
    protected abstract fun RemoteModel.convert(): LocalModel

    protected abstract suspend fun changelist(version: Int): List<StaticChangeList>
    protected abstract suspend fun delete(ids: Set<Int>)
    protected abstract suspend fun upsert(entities: List<LocalModel>)
    protected abstract suspend fun all(ids: Set<Int>): List<RemoteModel>

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeListSync(
            versionReader = ChangeListVersions::skillVersion,
            changeListFetcher = { currentVersion ->
                changelist(currentVersion)
            },
            versionUpdater = { latestVersion ->
                copy(skillVersion = latestVersion)
            },
            modelDeleter = { changeIds ->
                delete(
                    ids = changeIds.toSet()
                )
            },
            modelUpdater = { changeIds ->
                val models = all(ids = changeIds.toSet())
                    .map { it.convert() }
                upsert(
                    entities = models,
                )
            },
        )
    }
}