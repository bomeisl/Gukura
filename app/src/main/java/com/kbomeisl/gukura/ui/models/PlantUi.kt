package com.kbomeisl.gukura.ui.models

import com.kbomeisl.gukura.data.database.models.PlantDb

data class PlantUi(
    val name: String = "Loading...",
    val description: String = "Loading...",
    val temperature: String = "Loading...",
    val humidity: String = "Loading...",
    val lightLevel: String = "Loading...",
    val imageUrl: String = "",
    val garden: String = ""
)

