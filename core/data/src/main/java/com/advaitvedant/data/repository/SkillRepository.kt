package com.advaitvedant.data.repository

import com.advaitvedant.common.model.Skill
import com.advaitvedant.common.model.SkillWithData
import com.advaitvedant.data.utils.SyncStaticRepository
import com.advaitvedant.data.utils.asEntity
import com.advaitvedant.data.utils.asSkill
import com.advaitvedant.database.dao.SkillDaoImpl
import com.advaitvedant.database.model.SkillBase
import com.advaitvedant.database.model.SkillEntity
import com.advaitvedant.network.OpNetworkDataSource
import com.advaitvedant.network.model.SkillNetwork
import com.advaitvedant.network.model.StaticChangeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

abstract class SkillRepository : SyncStaticRepository<SkillEntity, SkillNetwork>() {
    abstract fun all(): Flow<List<Skill>>
    abstract fun get(id: Int): Flow<SkillWithData>
}
class FirstSkillRepository @Inject constructor(
    private val local: SkillDaoImpl,
    private val remote: OpNetworkDataSource,
) : SkillRepository() {

    override fun all(): Flow<List<Skill>>
        = local.all().map { it.map(SkillBase::asSkill) }

    override fun get(id: Int): Flow<SkillWithData>
        = local.get(id).map(SkillEntity::asSkill)

    override suspend fun all(ids: Set<Int>): List<SkillNetwork>
        = remote.all(ids)

    override fun SkillNetwork.convert(): SkillEntity = this.asEntity()

    override suspend fun changelist(version: Int): List<StaticChangeList>
        = remote.changeList(version)

    override suspend fun delete(ids: Set<Int>){
        local.delete(ids)
    }

    override suspend fun upsert(entities: List<SkillEntity>) {
        local.upsert(entities)
    }

}
