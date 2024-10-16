package com.kbomeisl.gukura.data.network

import kotlinx.serialization.Serializable

@Serializable
data class PlantNetwork(
    val name: String,
    val upperTemperature: Int,
    val lowerTemperature: Int,
    val upperHumidity: Int,
    val lowerHumidity: Int,
    val upperLightLevel: Int,
    val lowerLightLevel: Int
)
