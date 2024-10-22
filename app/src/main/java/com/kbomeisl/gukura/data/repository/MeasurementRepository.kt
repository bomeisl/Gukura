package com.kbomeisl.gukura.data.repository

import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.ui.models.MeasurementUi

class MeasurementRepository(private val measurementDao: MeasurementDao) {

    suspend fun saveMeasurementToDb(
        location: String,
        temperature: Float,
        humidity: Float,
        lightLevel: Float
    ) {
        val measurement = MeasurementDb(
            temperature = temperature,
            humidity = humidity,
            lightLevel = lightLevel,
            location = location,
            timestamp = Timestamp.now().toString()
        )
        measurementDao.upsertMeasurement(measurement)
    }

    //Retrieve all sensor measurements for a plant from the app database
    suspend fun getPlantMeasurements(plantName: String): List<MeasurementUi> {
        return measurementDao.findPlantMeasurements(plantName).map {
                measurementDb ->  measurementDb.toUi()
        }
    }
}