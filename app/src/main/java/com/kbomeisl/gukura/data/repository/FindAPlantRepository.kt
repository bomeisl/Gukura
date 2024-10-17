package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.PlantDb
import com.kbomeisl.gukura.data.network.PlantNetworkDataSource

class FindAPlantRepository(
    private val plantDataSource: PlantNetworkDataSource,
    private val plantDao: PlantDao
) {

    fun takeTemperatureReading() {
        //TODO(Grab temperature reading from smartphone sensor)
    }

    fun takeHumidityReading() {
        //TODO(Grab humidity reading from smartphone sensor)
    }

    fun takeLightReading() {
        //TODO(Grab light reading from smartphone sensor)
    }

    fun saveTemperatureReadingToDb() {
        //TODO(save a temperature reading to database)
    }

    fun saveHumidityReadingToDb() {
        //TODO(save a humidity reading to database)
    }

    fun saveLightReadingToDb() {
        //TODO(save a light reading to database)
    }

    suspend fun lookUpPlantApi(plantName: String) {
        val plant = plantDataSource.getPlant(name = plantName)
        //TODO(Do something with plant data retrieved from online API)
    }

    fun lookUpPlantDb(name: String) {
        val plant = plantDao.findPlant(plantName = name)
        //TODO(Do something with plant retrieved from app database)
    }

    //save a new plant to user's app database under 'My Plants'
    fun savePlant(plantDb: PlantDb) {
        plantDao.addPlant(plantDb)
    }

}