package com.kbomeisl.gukura.data.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [PlantDb::class], version = 1)
abstract class GukuraDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao
}