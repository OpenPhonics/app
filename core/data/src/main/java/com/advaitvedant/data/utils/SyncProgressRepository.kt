package com.advaitvedant.data.utils

import com.advaitvedant.database.model.EntityModel
import com.advaitvedant.database.model.ProgressChangeListEntity
import com.advaitvedant.database.utils.SyncProgressDao
import com.advaitvedant.network.SyncProgressService
import com.advaitvedant.network.model.NetworkModel
import kotlinx.coroutines.flow.first

abstract class SyncProgressRepository<LocalModel : EntityModel, RemoteModel : NetworkModel>(
    private val local: SyncProgressDao<LocalModel>,
    private val remote: SyncProgressService<RemoteModel>
) : Syncable {
    protected abstract fun LocalModel.asNetwork(): RemoteModel
    protected abstract fun RemoteModel.asEntity(): LocalModel
    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        return synchronizer.changeListSync(
            remoteProgressFetcher = {
                remote.changeList()
            },
            localProgressFetcher = {
                local.changeList()
                    .first()
                    .map(ProgressChangeListEntity::asNetwork)
            },
            modelLocalDeleter = { changeIds ->
                local.delete(
                    ids = changeIds.toSet()
                )
            },
            modelLocalUpdater = { changeIds ->
                val entities = remote.all(ids = changeIds.toSet())
                    .map { it.asEntity() }
                local.upsert(
                    entities = entities
                )
            },
            modelRemoteDeleter = { changeIds ->
                remote.delete(
                    ids = changeIds.toSet()
                )
            },
            modelRemoteUpdater = { changeIds ->
                val models = local.get(ids = changeIds.toSet())
                    .first()
                    .map { it.asNetwork() }
                remote.update(
                    models = models
                )
            }
        )
    }
}