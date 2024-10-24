package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.GardenUi

@Entity
data class GardenDb(
    @PrimaryKey(autoGenerate = true) val gardenId: Int,
    val name: String,
    var avgTemperature: Float,
    var avgHumidity: Float,
    var avgLightLevel: Float,
)

fun GardenDb.toUi(): GardenUi =
    GardenUi(
        name = name,
        avgTemperature = avgTemperature.toString(),
        avgHumidity = avgHumidity.toString(),
        avgLightLevel = avgLightLevel.toString()
    )
