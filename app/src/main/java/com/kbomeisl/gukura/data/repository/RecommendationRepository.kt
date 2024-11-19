package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.PlantUi

class RecommendationRepository(): BaseRepository() {


    suspend fun getAllPlants(): List<PlantUi> {
        return plantNetworkDataSource.getPlantList().map { it.toUi() }
    }
}