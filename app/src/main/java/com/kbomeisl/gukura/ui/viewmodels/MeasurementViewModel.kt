package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MeasurementViewModel(
    private val measurementDao: MeasurementDao,
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): PlantViewModel(plantRepository,gardenRepository) {
    val location = MutableStateFlow("")

    fun saveMeasurementToDb(
        temperature: Float,
        humidity: Float,
        lightLevel: Float,
        gardenName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            measurementDao.upsertMeasurement(
                MeasurementDb(
                temperature = temperature,
                humidity = humidity,
                lightLevel = lightLevel,
                garden = gardenName,
                timestamp = Timestamp.now().toString()
                )
            )
            gardenRepository.upsertGarden(
                GardenDb(
                    name = gardenName,
                    gardenId = gardenName.hashCode(),
                    avgTemperature = temperature.toString(),
                    avgHumidity = humidity.toString(),
                    avgLightLevel = lightLevel.toString()
                )
            )
        }
    }


}