package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.PlantDb

data class PlantUi(
    val name: String = "Loading...",
    val nickname: String = "Loading...",
    val description: String = "Loading...",
    val directions: String = "Loading...",
    val temperature: String = "Loading...",
    val humidity: String = "Loading...",
    val lightLevel: String = "Loading...",
    val imageUrl: String = "",
    var wishListed: Boolean = false,
    var dryWeight: Int = 0,
    var wetWeight: Int = 0,
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
        nickname = nickname,
        description = description,
        directions = directions,
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




