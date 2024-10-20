package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.ui.models.MeasurementUi

@Entity
data class MeasurementDb(
    @PrimaryKey(autoGenerate = true)
    val temperature: Float,
    val humidity: Float,
    val lightLevel: Float,
    val plantName: String,
    val timestamp: String
)

fun MeasurementDb.toUi(): MeasurementUi =
    MeasurementUi(
        temperature = temperature,
        humidity = humidity,
        lightLevel = lightLevel,
        plantName = plantName,
        timestamp = timestamp
    )
