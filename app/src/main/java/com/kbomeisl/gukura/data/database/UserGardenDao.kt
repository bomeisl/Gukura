package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.database.models.UserGardenDb

@Dao()
interface UserGardenDao {
    @Query("SELECT * FROM userGardenDb")
    fun getUserGardens(): List<UserGardenDb>

    @Upsert
    fun addPlant(plantDb: PlantDb)

    @Upsert
    fun addGarden(gardenDb: GardenDb)

    @Delete
    fun removePlant(plantDb: PlantDb)

    @Delete
    fun removeGarden(gardenDb: GardenDb)
}