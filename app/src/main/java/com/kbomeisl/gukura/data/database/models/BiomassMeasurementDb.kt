package com.kbomeisl.gukura.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.ui.models.BiomassMeasurementUi
import java.util.Date

@Entity
data class BiomassMeasurementDb(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val month: Int,
    val day: Int,
    val year: Int,
    val plantId: Int,
    val weight: Double
)

fun BiomassMeasurementDb.toUi(): BiomassMeasurementUi =
    BiomassMeasurementUi(
        date = Timestamp(date = Date(year, month, day)),
        plantId = plantId,
        weight = weight
    )