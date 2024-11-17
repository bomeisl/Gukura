package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MeasurementViewModel(
    private val measurementDao: MeasurementDao,
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository,
    private val weatherRepository: WeatherRepository
): PlantViewModel(plantRepository,gardenRepository) {
    val outsideHumidity = MutableStateFlow(0)
    val outsideTemperature = MutableStateFlow(0f)
    override val coroutineScope = viewModelScope

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

    fun getHumidity() {
        coroutineScope.launch(Dispatchers.IO) {
            val weather = weatherRepository.getWeather()
            outsideHumidity.value = weather.current.humidity
        }
    }

    fun getTemperature() {
        coroutineScope.launch(Dispatchers.IO) {
            val weather = weatherRepository.getWeather()
            outsideTemperature.value = weather.current.temp_f
        }
    }
}