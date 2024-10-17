package com.kbomeisl.gukura.data.network

import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.serialization.Serializable

@Serializable
data class PlantNetwork(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val maxTemperature: Int = 80,
    val minTemperature: Int = 50,
    val maxHumidity: Int = 50,
    val minHumidity: Int = 80,
    val maxLightLevel: Int = 2000,
    val minLightLevel: Int = 1000,
    val pictureLink: String = ""
)
fun PlantNetwork.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = Pair(minTemperature,maxTemperature),
        humidity = Pair(minHumidity,maxHumidity),
        lightLevel = Pair(minLightLevel,maxLightLevel),
        imageLink = pictureLink
    )
}

