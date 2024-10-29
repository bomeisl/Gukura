package com.kbomeisl.gukura.ui.testData

import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.ui.models.PlantUi

object plants {
    val plantList = listOf<PlantDb>(
        PlantDb(
            name = "Rose",
            plantId = "Rose".hashCode(),
            minTemperature = 50,
            maxTemperature = 60,
            minHumidity = 40,
            maxHumidity = 60,
            minLightLevel = 1000,
            maxLightLevel = 2000,
            gardenName = "garden",
            gardenTemp = "70",
            gardenHumidity = "50",
            gardenLightLevel = "60",
            imageUrl = "www.example.com",
            wishListed = false
        )
    )
}