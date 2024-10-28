package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.PlantDb

data class PlantUi(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val temperature: String = "Loading...",
    val humidity: String = "Loading...",
    val lightLevel: String = "Loading...",
    var imageUrl: String = "",
    var wishListed: Boolean = false,
    //Garden values
    var gardenName: String = "",
    var gardenTemp: String = "0",
    var gardenHumidity: String = "0",
    var gardenLightLevel: String = "0"
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
        imageUrl = imageUrl,
        wishListed = false,
        //Garden values
        gardenName = gardenName,
        gardenTemp = gardenTemp,
        gardenHumidity = gardenHumidity,
        gardenLightLevel = gardenLightLevel
    )




