package com.kbomeisl.gukura.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kbomeisl.gukura.data.database.models.BiomassMeasurementDb
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        PlantDb::class,
        MeasurementDb::class,
        GardenDb::class,
        BiomassMeasurementDb::class
               ],
    version = 21,
    exportSchema = false
)
abstract class GukuraDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao
    abstract fun measurementDao(): MeasurementDao
    abstract fun gardenDao(): GardenDao
    abstract fun biomassMeasurementDao(): BiomassMeasurementDao

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
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}