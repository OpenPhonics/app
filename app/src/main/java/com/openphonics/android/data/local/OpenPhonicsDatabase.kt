package com.openphonics.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openphonics.android.data.local.dao.DataDao
import com.openphonics.android.data.local.entity.data.base.EntityLanguageBase

object DatabaseMigrations {
    const val DB_VERSION = 2

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migration12()
        )

    private fun migration12(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE notes ADD COLUMN isPinned INTEGER NOT NULL")
        }
    }
}
@Database(
    entities = [EntityLanguageBase::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class OpenPhonicsDatabase : RoomDatabase() {

    abstract fun getDataDao(): DataDao

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
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
