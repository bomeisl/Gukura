package com.kbomeisl.gukura.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbomeisl.gukura.ui.models.PlantUi

@Entity
data class PlantDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String = "Loading...",
    val description: String = "Loading...",
    val temperature: Pair<Int,Int> = Pair(0,0),
    val humidity: Pair<Int,Int> = Pair(0,0),
    val lightLevel: Pair<Int,Int> = Pair(0,0),
    val imageLink: String=""
)

fun PlantDb.toUi(): PlantUi {
    return PlantUi(
        name = name,
        description = description,
        temperature = temperature,
        humidity = humidity,
        lightLevel = lightLevel,
        imageLink = imageLink
    )
}

