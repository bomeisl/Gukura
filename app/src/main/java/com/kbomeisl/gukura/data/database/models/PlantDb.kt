package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi

@Entity
data class PlantDb(
    val name: String = "Loading...",
    @PrimaryKey(autoGenerate = true) val plantId: Int = name.hashCode(),
    val description: String = "Loading...",
    val directions: String = "",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxLightLevel: Int = 0,
    val minLightLevel: Int = 0,
    var imageUrl: String="",
    var wishListed: Boolean = false,
    var dryWeight: Int = 0,
    var wetWeight: Int = 0,
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
        directions = directions,
        temperature = "${minTemperature}-${maxTemperature}",
        humidity = "${minHumidity}-${maxHumidity}",
        lightLevel = "${minLightLevel}-${maxLightLevel}",
        imageUrl = imageUrl,
        wishListed = false,
        dryWeight = dryWeight,
        wetWeight = wetWeight,
        //Garden values
        gardenName = gardenName,
        gardenTemp = gardenTemp,
        gardenHumidity = gardenHumidity,
        gardenLightLevel = gardenLightLevel
    )
}

