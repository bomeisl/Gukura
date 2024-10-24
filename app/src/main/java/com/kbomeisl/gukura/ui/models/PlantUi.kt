package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.PlantDb

data class PlantUi(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val temperature: String = "Loading...",
    val humidity: String = "Loading...",
    val lightLevel: String = "Loading...",
    val imageUrl: String = "",
    val garden: String = "",
    val wishListed: Boolean = false
)

fun PlantUi.toDb(): PlantDb =
    PlantDb(
        plantId = name.hashCode(),
        name = name,
        description = description,
        minTemperature = temperature.split("-").first().toInt(),
        maxTemperature = temperature.split("-").last().toInt(),
        minHumidity = humidity.split("-").first().toInt(),
        maxHumidity = humidity.split("-").last().toInt(),
        minLightLevel = lightLevel.split("-").first().toInt(),
        maxLightLevel = lightLevel.split("-").last().toInt(),
    )




