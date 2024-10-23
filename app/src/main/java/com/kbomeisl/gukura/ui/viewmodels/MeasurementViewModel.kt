package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.RecommendationRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MeasurementViewModel(
    private val measurementDao: MeasurementDao,
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): ViewModel() {
    val coroutineScope = viewModelScope

    val plantList = MutableStateFlow(listOf<PlantUi>())
    val gardenList = MutableStateFlow(listOf<GardenUi>())
    val currentGarden = mutableStateOf<GardenUi>(GardenUi())
    val location = MutableStateFlow("")

    fun saveMeasurementToDb(
        location: String,
        temperature: Float,
        humidity: Float,
        lightLevel: Float
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            measurementDao.upsertMeasurement(
                MeasurementDb(
                temperature = temperature,
                humidity = humidity,
                lightLevel = lightLevel,
                location = location,
                timestamp = Timestamp.now().toString()
                )
            )
            Log.d("Measurements","${temperature}")
        }
    }

    fun populatePlantList(
        temperature: Float,
        humidity: Float,
        lightLevel: Float,
        location: String
    ) {
        coroutineScope.launch {
            plantList.value =
                plantRepository.getPlantsInRange(temperature, humidity, lightLevel)
        }
    }

    fun populateGardenList() {
        coroutineScope.launch {
            gardenList.value = gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }
}