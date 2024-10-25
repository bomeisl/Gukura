package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.PlantDb

data class PlantUi(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val temperature: String = "Loading...",
    val humidity: String = "Loading...",
    val lightLevel: String = "Loading...",
    var imageUrl: String = "",
    var garden: String = "",
    var wishListed: Boolean = false
)

fun PlantUi.toDb(): PlantDb =
    PlantDb(
        plantId = name.hashCode(),
        name = name,
        description = description,
        minTemperature = temperature.split("-").first().toFloat().toInt(),
        maxTemperature = temperature.split("-").last().toFloat().toInt(),
        minHumidity = humidity.split("-").first().toFloat().toInt(),
        maxHumidity = humidity.split("-").last().toFloat().toInt(),
        minLightLevel = lightLevel.split("-").first().toFloat().toInt(),
        maxLightLevel = lightLevel.split("-").last().toFloat().toInt(),
        garden = garden
    )




