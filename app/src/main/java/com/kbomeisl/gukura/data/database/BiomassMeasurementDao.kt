package com.kbomeisl.gukura.data.database

import androidx.room.Dao
import androidx.room.Query
import com.kbomeisl.gukura.data.database.models.BiomassMeasurementDb
import com.kbomeisl.gukura.data.database.models.PlantDb

@Dao
interface BiomassMeasurementDao {
    @Query("SELECT * FROM biomassmeasurementdb WHERE plantId = :plantId")
    fun getBiomassMeasurementByPlantName(plantId: Int): BiomassMeasurementDb
}