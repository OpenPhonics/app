package com.advaitvedant.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.advaitvedant.database.dao.PhoneticLessonDao
import com.advaitvedant.database.dao.SentenceDao
import com.advaitvedant.database.dao.WordDao
import com.advaitvedant.database.model.LessonStateConverter
import com.advaitvedant.database.model.PhoneticLessonChangeListEntity
import com.advaitvedant.database.model.PhoneticLessonEntity
import com.advaitvedant.database.model.PhoneticLessonSentenceCrossRef
import com.advaitvedant.database.model.PhoneticLessonWordCrossRef
import com.advaitvedant.database.model.SentenceChangeListEntity
import com.advaitvedant.database.model.SentenceEntity
import com.advaitvedant.database.model.SentenceWordCrossRef
import com.advaitvedant.database.model.WordChangeListEntity
import com.advaitvedant.database.model.WordEntity

@Database(
    entities = [
        PhoneticLessonSentenceCrossRef::class,
        PhoneticLessonWordCrossRef::class,
        PhoneticLessonEntity::class,
        PhoneticLessonChangeListEntity::class,
        WordEntity::class,
        WordChangeListEntity::class,
        SentenceEntity::class,
        SentenceChangeListEntity::class,
        SentenceWordCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LessonStateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phoneticLessonDao(): PhoneticLessonDao

    abstract fun sentenceDao(): SentenceDao

    abstract fun wordDao(): WordDao
}
