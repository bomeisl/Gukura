package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.GardenDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens

class GardenRepository(
    private val gardenDao: GardenDao,
) {
    fun getAllGardens(): List<GardenDb> = gardenDao.getAllGardens()

    suspend fun getGardenByName(gardenName: String): GardenDb = gardenDao.getGardenByName(gardenName)

    suspend fun populateDb() {
        gardens.gardenList.forEach {
            gardenDao.upsertGarden(it.toDb())
        }
    }

    suspend fun addPlantsToGarden() {

    }

}