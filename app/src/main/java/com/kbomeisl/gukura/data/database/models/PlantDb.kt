package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlin.random.Random

@Entity
data class PlantDb(
    val name: String = "Loading...",
    @PrimaryKey(autoGenerate = true) val plantId: Int = name.hashCode(),
    val description: String = "Loading...",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxLightLevel: Int = 0,
    val minLightLevel: Int = 0,
    var imageUrl: String="",
    var wishListed: Boolean = false,
    //Garden values
    var gardenName: String = "",
    var gardenTemp: String = "0",
    var gardenHumidity: String = "0",
    var gardenLightLevel: String = "0"
)

fun PlantDb.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = "${minTemperature}-${maxTemperature}",
        humidity = "${minHumidity}-${maxHumidity}",
        lightLevel = "${minLightLevel}-${maxLightLevel}",
        imageUrl = imageUrl,
        wishListed = false,
        //Garden values
        gardenName = gardenName,
        gardenTemp = gardenTemp,
        gardenHumidity = gardenHumidity,
        gardenLightLevel = gardenLightLevel
    )
}

