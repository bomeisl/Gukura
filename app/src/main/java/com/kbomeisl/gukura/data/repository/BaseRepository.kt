package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.ui.models.PlantUi

open class BaseRepository {

    suspend fun getPlantsInRange(
        temperature: Float,
        humidity: Float,
        lightLevel: Float
    ): List<PlantUi> {
        return plantNetworkDataSource.getPlantList()
            .filter {
                if (it.tempMin != null && it.tempMax != null) {
                    temperature > it.tempMin!! && temperature < it.tempMax!!
                } else {
                    true
                }
            }
            .filter {
                if (it.humidityMin != null && it.humidityMax != null) {
                    temperature > it.humidityMin!! && temperature < it.humidityMax!!
                } else {
                    true
                }
            }
            .filter {
                if (it.lightMin != null && it.lightMax != null) {
                    temperature > it.lightMin!! && temperature < it.lightMax!!
                } else {
                    true
                }
            }
            .map { it.toUi() }
    }
}