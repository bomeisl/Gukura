package com.kbomeisl.gukura.ui.models

import com.google.firebase.Timestamp

data class MeasurementUi(
    val temperature: Float,
    val humidity: Float,
    val lightLevel: Float,
    val timestamp: String,
    val location: String
)
