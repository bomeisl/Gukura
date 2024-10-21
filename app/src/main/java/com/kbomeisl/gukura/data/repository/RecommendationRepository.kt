package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.PlantUi

class RecommendationRepository {
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

    suspend fun getAllPlants(): List<PlantUi> {
        return plantNetworkDataSource.getPlantList().map { it.toUi() }
    }
}