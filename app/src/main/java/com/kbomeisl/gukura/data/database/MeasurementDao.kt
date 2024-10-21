package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.database.models.PlantDb

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM measurementDb WHERE timestamp = :timestamp")
    fun findMeasurementByTimestamp(timestamp: String): MeasurementDb

    @Query("SELECT * FROM measurementdb WHERE location = :location")
    fun findPlantMeasurements(location: String): List<MeasurementDb>

    @Insert
    fun saveMeasurement(measurementDb: MeasurementDb)

    @Upsert
    fun upsertMeasurement(measurementDb: MeasurementDb)
}