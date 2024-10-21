package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.ui.models.MeasurementUi
import kotlin.random.Random

@Entity
data class MeasurementDb(
    @PrimaryKey(autoGenerate = true) val id: Int = Random.nextInt(),
    val temperature: Float,
    val humidity: Float,
    val lightLevel: Float,
    val location: String,
    val timestamp: String
)

fun MeasurementDb.toUi(): MeasurementUi =
    MeasurementUi(
        temperature = temperature,
        humidity = humidity,
        lightLevel = lightLevel,
        location = location,
        timestamp = timestamp
    )
