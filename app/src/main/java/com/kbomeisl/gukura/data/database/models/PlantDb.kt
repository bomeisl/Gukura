package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi

@Entity
data class PlantDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String = "Loading...",
    val description: String = "Loading...",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxSunlight: Int = 0,
    val minSunlight: Int = 0,
    val imageLink: String=""
)

fun PlantDb.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = Pair(minTemperature,maxTemperature),
        humidity = Pair(minHumidity,maxHumidity),
        lightLevel = Pair(minSunlight,maxSunlight),
        imageLink = imageLink
    )
}

