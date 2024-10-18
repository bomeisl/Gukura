package com.kbomeisl.gukura.data.network

import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.serialization.Serializable

@Serializable
data class PlantNetwork(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxLightLevel: Int = 0,
    val minLightLevel: Int = 0,
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

