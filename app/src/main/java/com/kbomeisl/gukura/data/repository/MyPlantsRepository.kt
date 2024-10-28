package com.kbomeisl.gukura.data.repository

import android.util.Log
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import com.kbomeisl.gukura.data.network.plantNetworkDataSource

class MyPlantsRepository(
    private val plantDao: PlantDao
) {

    fun getMyPlants() {
        //retrieve a list of user's plants from db
    }

    //get the data for the user's houseplants from our Web API
    suspend fun getPlants(plantList: List<String>): List<PlantNetwork> {
        Log.d("Repository", "Plant List fetched")
        return plantNetworkDataSource.getPlantsByNames(plantList)
    }

    suspend fun upsertPlant(plantNetwork: PlantNetwork) {
        plantDao.upsertPlant(plantDb = plantNetwork.toDb())
    }
}