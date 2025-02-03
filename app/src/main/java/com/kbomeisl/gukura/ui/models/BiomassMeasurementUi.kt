package com.kbomeisl.gukura.ui.models

import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.models.BiomassMeasurementDb
import java.time.Instant
import java.util.Date

data class BiomassMeasurementUi(
    val date: Timestamp = Timestamp.now(),
    val plantId: Int,
    val weight: Double = 0.0
)

fun BiomassMeasurementUi.toDb(): BiomassMeasurementDb =
    BiomassMeasurementDb(
        year = date.toDate().year,
        month = date.toDate().month,
        day = date.toDate().date,
        plantId = plantId,
        weight = weight,
    )