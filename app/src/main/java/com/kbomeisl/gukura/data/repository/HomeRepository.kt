package com.kbomeisl.gukura.data.repository

import android.util.Log
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.PlantDb
import com.kbomeisl.gukura.data.network.plantNetworkDataSource

class HomeRepository(
    private val plantDao: PlantDao
) {
    val logTag = "HomeRespository"

    fun getMyPlants(): List<PlantDb> {
        var myPlants = listOf<PlantDb>()
        try {
            myPlants = plantDao.listAllPlants()
        } catch (exception: Exception) {
            Log.e(logTag, "list plants db")
        }
        return myPlants
    }
}