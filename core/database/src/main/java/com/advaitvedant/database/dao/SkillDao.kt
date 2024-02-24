package com.advaitvedant.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.advaitvedant.database.model.SkillEntity
import com.advaitvedant.database.model.SkillBase
import com.advaitvedant.database.model.SkillSentenceBase
import com.advaitvedant.database.model.SkillSentenceEntity
import com.advaitvedant.database.model.SkillSentenceSkillWordCrossRef
import com.advaitvedant.database.model.SkillSkillSentenceCrossRef
import com.advaitvedant.database.model.SkillSkillWordCrossRef
import com.advaitvedant.database.model.SkillWordEntity
import com.advaitvedant.database.utils.SyncStaticDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillDaoImpl @Inject constructor(private val skillDao: SkillDao) {
        suspend fun upsert(entities: List<SkillEntity>) {
            skillDao.upsertSkillBase(entities.map(SkillEntity::skill))
            entities.map(SkillEntity::words).map { skillDao.upsertSkillWord(it) }
            entities.map(SkillEntity::sentences).map { skillDao.upsertSkillSentenceBase(it.map(SkillSentenceEntity::sentence)) }
            skillDao.insertSkillSentenceWordCrossRef(
                entities.map(SkillEntity::sentences)
                    .flatten()
                    .map { sentence ->
                        sentence.words.map { word ->
                            SkillSentenceSkillWordCrossRef(word.id, sentence.sentence.id)
                        }
                    }
                    .flatten()
            )
            skillDao.insertSkillWordCrossRef(
                entities.map { skill ->
                    skill.words.map { word ->
                        SkillSkillWordCrossRef(word.id, skill.skill.id)
                    }
                }.flatten()
            )
            skillDao.insertSkillSentenceCrossRef(
                entities.map { skill ->
                    skill.sentences.map { sentence ->
                        SkillSkillSentenceCrossRef(sentence.sentence.id, skill.skill.id)
                    }
                }.flatten()
            )
    }

    suspend fun delete(ids: Set<Int>) {
        skillDao.delete(ids)
    }

    fun all(): Flow<List<SkillBase>>
        = skillDao.all()
    fun get(id: Int): Flow<SkillEntity>
        = skillDao.get(id)

}
@Dao
interface SkillDao  {
    @Transaction
    @Query("SELECT * FROM skills WHERE :id = id")
    fun get(id: Int): Flow<SkillEntity>
    @Transaction
    @Query("SELECT * FROM skills")
    fun all(): Flow<List<SkillBase>>


    @Query("DELETE FROM skills WHERE id IN (:ids)")
    suspend fun delete(ids: Set<Int>)

    @Upsert
    suspend fun upsertSkillBase(entities: List<SkillBase>)

    @Upsert
    suspend fun upsertSkillWord(entities: List<SkillWordEntity>)

    @Upsert
    suspend fun upsertSkillSentenceBase(entities: List<SkillSentenceBase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkillWordCrossRef(crossRefs: List<SkillSkillWordCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkillSentenceCrossRef(crossRefs: List<SkillSkillSentenceCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkillSentenceWordCrossRef(crossRefs: List<SkillSentenceSkillWordCrossRef>)
}
