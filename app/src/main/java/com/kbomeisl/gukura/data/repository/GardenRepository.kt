package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.database.GardenDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.ui.models.PlantUi

class GardenRepository(
    private val gardenDao: GardenDao
) {
    suspend fun getAllGardens(): List<GardenDb> {
        return gardenDao.getAllGardens()
    }
}