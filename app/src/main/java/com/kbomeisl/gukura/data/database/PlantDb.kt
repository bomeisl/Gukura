package com.kbomeisl.gukura.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlantDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "temperature") val temperature: Pair<Int,Int>,
    @ColumnInfo(name = "humidity") val humidity: Pair<Int,Int>,
    @ColumnInfo(name = "lightLevel") val lightLevel: Pair<Int,Int>
)
