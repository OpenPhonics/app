package com.openphonics.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openphonics.data.local.dao.*
import com.openphonics.data.local.entity.*

@Database(
    entities = [FlagEntity::class, LanguageEntity::class, WordEntity::class],
    version = 1
)
abstract class OpenPhonicsDatabase : RoomDatabase() {

    abstract fun getFlagDao(): FlagDao
    abstract  fun getLanguageDao(): LanguageDao
    abstract fun getWordDao(): WordDao

    companion object {
        private const val DB_NAME = "openphonics_database"

        @Volatile
        private var INSTANCE: OpenPhonicsDatabase? = null

        fun getInstance(context: Context): OpenPhonicsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OpenPhonicsDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
