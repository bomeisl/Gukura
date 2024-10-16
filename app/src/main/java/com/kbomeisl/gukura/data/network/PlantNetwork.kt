package com.kbomeisl.gukura.data.network

import kotlinx.serialization.Serializable

@Serializable
data class PlantNetwork(
    val name: String,
    val temperature: Pair<Int,Int>,
    val humidity: Pair<Int,Int>,
    val lightLevel: Pair<Int,Int>
)
