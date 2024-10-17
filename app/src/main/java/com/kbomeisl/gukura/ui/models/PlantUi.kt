package com.kbomeisl.gukura.ui.models

data class PlantUi(
    val name: String,
    val description: String,
    val temperature: Pair<Int,Int>,
    val humidity: Pair<Int,Int>,
    val lightLevel: Pair<Int,Int>,
    val pictureLink: String
)
