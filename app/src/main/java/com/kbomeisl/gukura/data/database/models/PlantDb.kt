package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlin.random.Random

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = GardenDb::class,
            parentColumns = ["gardenId"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class PlantDb(
    @PrimaryKey(autoGenerate = true) val id: Int = Random.nextInt(),
    val name: String = "Loading...",
    val description: String = "Loading...",
    val maxTemperature: Int = 0,
    val minTemperature: Int = 0,
    val maxHumidity: Int = 0,
    val minHumidity: Int = 0,
    val maxLightLevel: Int = 0,
    val minLightLevel: Int = 0,
    val imageUrl: String="",
    val garden: String="None"
)

fun PlantDb.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = "${minTemperature}-${maxTemperature}",
        humidity = "${minHumidity}-${maxHumidity}",
        lightLevel = "${minLightLevel}-${maxLightLevel}",
        imageUrl = imageUrl,
        garden = garden
    )
}

