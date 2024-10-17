package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM plantdb")
    fun listAllPlants(): List<PlantDb>

    @Query("SELECT * FROM plantdb WHERE name = :plantName")
    fun findPlant(plantName: String): PlantDb

    @Insert
    suspend fun addPlant(plantDb: PlantDb)

    @Delete
    suspend fun deletePlant(plantDb: PlantDb)
}