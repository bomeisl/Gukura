package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.PlantUi

class PlantRepository(
    private val plantDao: PlantDao
) {

    //fetch all plants from the online database
    suspend fun getAllPlantsNetwork(): List<PlantNetwork> {
        return plantNetworkDataSource.getPlantList()
    }

    //retrieve all plants from the app database
    fun getAllPlantsDb(): List<PlantUi> {
        return plantDao.listAllPlants().map { it.toUi() }
    }

    //retrieve a plant from the app database by name
    fun getPlantDb(plantName: String): PlantUi {
        return plantDao.findPlant(plantName).toUi()
    }

    //fetch plants from online database and cache them in app database
    suspend fun cachePlantsInAppDatabase(): List<PlantUi> {
        val plants = getAllPlantsNetwork()
        plants.forEach {
            plantDao.upsertPlant(it.toDb())
        }
        return plants.map { it.toUi() }
    }

    //fetch a plant from the online database
    suspend fun getPlantNetwork(plantName: String): PlantUi {
      return plantNetworkDataSource.getPlantsByNames(listOf(plantName))
          .first()
          .toUi()
    }

    suspend fun getPlantsInRange(
        temperature: Float,
        humidity: Float,
        lightLevel: Float
    ): List<PlantUi> {
        return plantNetworkDataSource.getPlantList()
            .filter { temperature > it.tempMin-10 && temperature < it.tempMax+10 }
            .filter { humidity > it.humidityMin-10 && humidity < it.humidityMax+10 }
            .filter { lightLevel > it.lightMin-1000 && lightLevel < it.lightMax+1000 }
            .map { it.toUi() }
    }
}