package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.GardenUi

@Entity
data class GardenDb(
    @PrimaryKey(autoGenerate = true) val gardenId: Int,
    val name: String=" ",
    var avgTemperature: String="0",
    var avgHumidity: String="0",
    var avgLightLevel: String="",
    var windowDirection: String=""
)

fun GardenDb.toUi(): GardenUi =
    GardenUi(
        name = name,
        avgTemperature = avgTemperature,
        avgHumidity = avgHumidity,
        avgLightLevel = avgLightLevel,
        windowDirection = windowDirection
    )
