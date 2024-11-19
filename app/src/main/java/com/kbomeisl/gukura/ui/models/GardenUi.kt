package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.GardenDb

data class GardenUi(
    val name: String="",
    val avgTemperature: String="",
    val avgHumidity: String="",
    val avgLightLevel: String="",
    var windowDirection: String="",
    val plants: List<PlantUi> = listOf<PlantUi>()
)

fun GardenUi.toDb(): GardenDb =
    GardenDb(
        gardenId = name.hashCode(),
        name = name,
        avgTemperature = avgTemperature,
        avgHumidity = avgHumidity,
        avgLightLevel = avgLightLevel,
        windowDirection = windowDirection
    )