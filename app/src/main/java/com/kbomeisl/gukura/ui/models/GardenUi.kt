package com.kbomeisl.gukura.ui.models

data class GardenUi(
    val name: String,
    val avgTemperature: String,
    val avgHumidity: String,
    val avgLightLevel: String,
    val plants: List<PlantUi>
)
