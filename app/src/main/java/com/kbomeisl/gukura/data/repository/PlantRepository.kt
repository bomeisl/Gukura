package com.kbomeisl.gukura.data.repository

import android.util.Log
import com.kbomeisl.gukura.data.database.GardenDao
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.PlantUi

class PlantRepository(
    private val plantDao: PlantDao,
    private val gardenDao: GardenDao
):BaseRepository() {
    private val logTag = "Plant Repository"
    var failedDbInsertPlantList = mutableListOf<PlantDb>()

    //fetch all plants from the online database
    suspend fun getAllPlantsNetwork(): List<PlantNetwork> {
        return plantNetworkDataSource.getPlantList()
    }

    suspend fun cacheAllPlants() {
        val plants = getAllPlantsNetwork()
        plants.forEach {
            try {
                plantDao.upsertPlant(it.toDb())
            } catch (e: Exception) {
                Log.e(logTag, "Insert Dao exception")
                //failedDbInsertPlantList.add(it.toDb())
            }

        }
    }

    suspend fun getAllPlantsAndCache(): List<PlantNetwork> {
        val plants = plantNetworkDataSource.getPlantList()
        plants.forEach {
            plantDao.upsertPlant(it.toDb())
        }
        return plants
    }

    //retrieve all plants from the app database
    suspend fun getAllPlantsDb(): List<PlantDb> {
        return plantDao.listAllPlants()
    }

    //retrieve a plant from the app database by name
    suspend fun getPlantDb(plantName: String): PlantDb {
        return plantDao.findPlant(plantName)
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

    suspend fun upsertPlant(plantDb: PlantDb) {
        plantDao.upsertPlant(plantDb = plantDb)
    }

    suspend fun deleteGarden(plantDb: PlantDb) {
        plantDao.updatePlant(
            plantDb = PlantDb(
                plantId = plantDb.plantId,
                name = plantDb.name,
                description = plantDb.description,
                minTemperature = plantDb.minTemperature,
                maxTemperature = plantDb.maxTemperature,
                minHumidity = plantDb.minHumidity,
                maxHumidity = plantDb.maxHumidity,
                minLightLevel = plantDb.minLightLevel,
                maxLightLevel = plantDb.maxLightLevel,
                imageUrl = plantDb.imageUrl,
                wishListed = plantDb.wishListed,
                gardenName = "",
                gardenTemp = "null",
                gardenHumidity = "null",
                gardenLightLevel = "null"
            )
        )
    }

    suspend fun addGarden(plantDb: PlantDb, gardenDb: GardenDb) {
        plantDao.updatePlant(
            plantDb = PlantDb(
                plantId = plantDb.plantId,
                name = plantDb.name,
                description = plantDb.description,
                minTemperature = plantDb.minTemperature,
                maxTemperature = plantDb.maxTemperature,
                minHumidity = plantDb.minHumidity,
                maxHumidity = plantDb.maxHumidity,
                minLightLevel = plantDb.minLightLevel,
                maxLightLevel = plantDb.maxLightLevel,
                imageUrl = plantDb.imageUrl,
                wishListed = plantDb.wishListed,
                gardenName = gardenDb.name,
                gardenTemp = gardenDb.avgTemperature,
                gardenHumidity = gardenDb.avgHumidity,
                gardenLightLevel = gardenDb.avgLightLevel
            )
        )
    }

    suspend fun purgePlants(plantList: List<PlantDb>) {
        plantList.forEach {
            plantDao.deletePlant(it)
        }
    }
}