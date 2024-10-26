package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kbomeisl.gukura.data.database.models.GardenDb

@Dao
interface GardenDao {
    @Query("SELECT * FROM gardendb")
    fun getAllGardens(): List<GardenDb>

    @Query("SELECT * FROM gardendb WHERE name = :gardenName")
    fun getGardenByName(gardenName: String): GardenDb

    @Upsert
    fun upsertGarden(gardenDb: GardenDb)
}