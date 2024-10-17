package com.kbomeisl.gukura.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import kotlinx.coroutines.CoroutineScope

@Database(entities = [PlantDb::class], version = 1)
abstract class GukuraDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: GukuraDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GukuraDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    GukuraDatabase::class.java,
                    "gukuraDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}