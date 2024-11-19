package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.GardenDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens

class GardenRepository(
    private val gardenDao: GardenDao,
) {
    suspend fun getAllGardens(): List<GardenDb> = gardenDao.getAllGardens()

    suspend fun getGardenByName(gardenName: String): GardenDb = gardenDao.getGardenByName(gardenName)

    suspend fun addPlantsToGarden() {

    }

    suspend fun upsertGarden(gardenDb: GardenDb) {
        gardenDao.upsertGarden(gardenDb = gardenDb)
    }

    suspend fun deleteGarden(gardenDb: GardenDb) {
        gardenDao.deleteGarden(gardenDb = gardenDb)
    }


}