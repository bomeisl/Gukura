package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.sensor.sensorDataSource
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FindAPlantViewModel(
    val findAPlantRepository: FindAPlantRepository,
): ViewModel() {
    private val coroutineScope = viewModelScope

    val plantList = MutableStateFlow<List<PlantUi>>(listOf())

    fun getPlantsByName(plantName: String) {
        coroutineScope.launch {
            plantList.value = findAPlantRepository.getPlantListApi()
                .map { it.toUi() }
                .filter { it.name.contains(plantName) }
        }
    }

    //Get plants from Gukura's online database that would thrive at a given temperature
    fun getPlantsFromFirebaseByTemperature(temperature: Float): Deferred<List<PlantUi>> {
        val plantTempNetworkJob = coroutineScope.async {
            findAPlantRepository.getPlantListApi().filter {
                it.tempMin < temperature && it.tempMax > temperature
            }.map {
                plantNetwork -> plantNetwork.toUi()
            }
        }
        return plantTempNetworkJob
    }

    //Get plants from Gukura's online database that would thrive at a given humidity
    fun getPlantsFromFirebaseByHumidity(humidity: Float): Deferred<List<PlantUi>> {
        val plantHumidityNetworkJob = coroutineScope.async {
            findAPlantRepository.getPlantListApi().filter {
                it.humidityMin < humidity && it.humidityMax > humidity
            }.map {
                plantNetwork -> plantNetwork.toUi()
            }
        }
        return plantHumidityNetworkJob
    }

    //Get plants from Gukura's online database that would thrive at a given level of sunlight
    fun getPlantsFromFirebaseByLightLevel(lightLevel: Float): Deferred<List<PlantUi>> {
        val plantLightNetworkJob = coroutineScope.async {
            findAPlantRepository.getPlantListApi().filter {
                it.lightMin < lightLevel && it.lightMax > lightLevel
            }.map {
                plantNetwork -> plantNetwork.toUi()
            }
        }
        return plantLightNetworkJob
    }

    fun getPlantFromDatabase(plantName: String): PlantUi {
        return findAPlantRepository.lookUpPlantDb(plantName = plantName).toUi()
    }

    fun saveMeasurementToDb(temperature: Float, humidity: Float, lightLevel: Float) {

    }
}