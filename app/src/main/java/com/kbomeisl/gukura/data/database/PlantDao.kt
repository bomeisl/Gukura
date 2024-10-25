package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.kbomeisl.gukura.data.database.models.PlantDb

@Dao
interface PlantDao {
    @Query("SELECT * FROM plantdb")
    fun listAllPlants(): List<PlantDb>

    @Query("SELECT * FROM plantdb WHERE name = :plantName")
    fun findPlant(plantName: String): PlantDb

    @Upsert
    suspend fun upsertPlant(plantDb: PlantDb)

    @Update
    suspend fun updatePlant(plantDb: PlantDb)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPlant(plantDb: PlantDb)

    @Delete
    suspend fun deletePlant(plantDb: PlantDb)
}