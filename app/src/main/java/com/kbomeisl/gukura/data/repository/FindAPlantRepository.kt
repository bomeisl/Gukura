package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.PlantDb
import com.kbomeisl.gukura.data.network.PlantNetwork
import com.kbomeisl.gukura.data.network.plantNetworkDataSource

class FindAPlantRepository(
    private val plantDao: PlantDao
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
}