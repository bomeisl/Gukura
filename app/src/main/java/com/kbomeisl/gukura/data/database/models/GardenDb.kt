package com.kbomeisl.gukura.data.database.models

data class GardenDb(
    val name: String,
    val location: String,
    val plants: List<PlantDb>,
)
