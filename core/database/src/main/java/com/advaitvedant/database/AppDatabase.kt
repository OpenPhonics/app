package com.advaitvedant.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.advaitvedant.database.dao.SkillDao
import com.advaitvedant.database.utils.EntityConverter
import com.advaitvedant.database.model.ProgressChangeListEntity
import com.advaitvedant.database.model.SkillBase
import com.advaitvedant.database.model.SkillSentenceBase
import com.advaitvedant.database.model.SkillSentenceSkillWordCrossRef
import com.advaitvedant.database.model.SkillSkillSentenceCrossRef
import com.advaitvedant.database.model.SkillSkillWordCrossRef
import com.advaitvedant.database.model.SkillWordEntity

@Database(
    entities = [
        SkillBase::class,
        SkillWordEntity::class,
        SkillSentenceBase::class,
        SkillSentenceSkillWordCrossRef::class,
        SkillSkillWordCrossRef::class,
        SkillSkillSentenceCrossRef::class,
        ProgressChangeListEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(EntityConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun skillDao(): SkillDao
}
