package com.kbomeisl.gukura.data.repository

import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.MeasurementUi
import java.util.Date

class FindAPlantRepository(
    private val plantDao: PlantDao,
    private val measurementDao: MeasurementDao
) {
    suspend fun getPlantListApi(): List<PlantNetwork> {
        return plantNetworkDataSource.getPlantList()
    }

    fun indexPlantNames(plantList: List<PlantNetwork>): List<String> {
        return plantList.map { plant -> plant.name }
    }

    //Look up a plant in the online database
    suspend fun lookUpPlantApi(plantName: String): PlantNetwork {
        //check if the plant is in the online database
        var plantIsInDatabase: Boolean = false
        val plantNameIndex = indexPlantNames(getPlantListApi())
        if (plantNameIndex.contains(plantName)) { plantIsInDatabase = true }

        if (plantIsInDatabase) {
            return plantNetworkDataSource.getPlant(name = plantName)
        } else {
            return PlantNetwork()
        }
    }

    fun getPlantListDb(): List<PlantDb> {
        return plantDao.listAllPlants()
    }

    fun indexPlantNamesDb(plantList: List<PlantDb>): List<String> {
        return plantList.map { plant -> plant.name }
    }

    //Look up a plant in the app's database
    fun lookUpPlantDb(plantName: String): PlantDb {
        return plantDao.findPlant(plantName = plantName)
    }

    //add a new plant to the app's database
    suspend fun addPlant(plantDb: PlantDb) {
        plantDao.addPlant(plantDb)
    }

    //Save a sensor measurement to the app database for a given plant
    suspend fun saveMeasurementToDb(
        plantName: String,
        temperature: Float,
        humidity: Float,
        lightLevel: Float
    ) {
        val measurement = MeasurementDb(
            temperature = temperature,
            humidity = humidity,
            lightLevel = lightLevel,
            plantName = plantName,
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