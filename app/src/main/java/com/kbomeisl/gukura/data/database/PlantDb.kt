package com.kbomeisl.gukura.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlantDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val temperature: Pair<Int,Int>,
    val humidity: Pair<Int,Int>,
    val lightLevel: Pair<Int,Int>,
    val imageLink: String
)
