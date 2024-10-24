package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlin.random.Random

@Entity
data class PlantDb(
    @PrimaryKey(autoGenerate = true) val plantId: Int,
    val name: String = "Loading...",
    val description: String = "Loading...",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxLightLevel: Int = 0,
    val minLightLevel: Int = 0,
    val imageUrl: String="",
    val wishListed: Boolean = false
)

fun PlantDb.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = "${minTemperature}-${maxTemperature}",
        humidity = "${minHumidity}-${maxHumidity}",
        lightLevel = "${minLightLevel}-${maxLightLevel}",
        imageUrl = imageUrl,
    )
}

