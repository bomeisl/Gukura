package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.PlantDb
import com.kbomeisl.gukura.data.network.PlantNetwork
import com.kbomeisl.gukura.data.network.PlantNetworkDataSource

class FindAPlantRepository(
    private val plantDataSource: PlantNetworkDataSource,
    private val plantDao: PlantDao
) {
    //Look up a plant in the online database
    suspend fun lookUpPlantApi(plantName: String): PlantNetwork {
        return plantDataSource.getPlant(name = plantName)
    }

    //Look up a plant in the app's database
    fun lookUpPlantDb(name: String): PlantDb {
        return plantDao.findPlant(plantName = name)
    }

    //add a new plant to the app's database
    suspend fun addPlant(plantDb: PlantDb) {
        plantDao.addPlant(plantDb)
    }
}