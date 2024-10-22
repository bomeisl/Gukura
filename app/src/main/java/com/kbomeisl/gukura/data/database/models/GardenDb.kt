package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class GardenDb(
    @PrimaryKey val gardenId: Int,
    val name: String,
    var avgTemperature: Float,
    var avgHumidity: Float,
    var avgLightLevel: Float,
)
