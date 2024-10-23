package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Query
import com.kbomeisl.gukura.data.database.models.GardenDb

@Dao
interface GardenDao {
    @Query("SELECT * FROM gardendb")
    fun getAllGardens(): List<GardenDb>
}