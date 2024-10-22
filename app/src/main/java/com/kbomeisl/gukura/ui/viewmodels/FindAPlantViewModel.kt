package com.kbomeisl.gukura.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FindAPlantViewModel(
    private val plantRepository: PlantRepository,
): ViewModel() {
    private val coroutineScope = viewModelScope

    val plantList = MutableStateFlow<List<PlantUi>>(listOf())
    var plantLifeType = mutableStateOf("")
    var plantFlowerType = mutableStateOf("")
    var plantSizeType = mutableStateOf("")
    var garden = mutableStateOf("")

    fun populatePlantList() {
        coroutineScope.launch {
            plantList.value = plantRepository.getAllPlantsNetwork().map { it.toUi() }
        }
    }

    fun getPlantsByName(plantName: String) {
        coroutineScope.launch {
            plantList.value = plantRepository.getAllPlantsNetwork()
                .filter { it.name.contains(plantName, ignoreCase = true) }
                .map { it.toUi() }
        }
    }

    //Get plants from Gukura's online database that would thrive at a given temperature
    fun getPlantsFromFirebaseByTemperature(temperature: Float): Deferred<List<PlantUi>> {
        val plantTempNetworkJob = coroutineScope.async {
            plantRepository.getAllPlantsNetwork().filter {
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
            plantRepository.getAllPlantsNetwork().filter {
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
            plantRepository.getAllPlantsNetwork().filter {
                it.lightMin < lightLevel && it.lightMax > lightLevel
            }.map {
                plantNetwork -> plantNetwork.toUi()
            }
        }
        return plantLightNetworkJob
    }

    fun getPlantFromDatabase(plantName: String): PlantUi {
        return plantRepository.getPlantDb(plantName = plantName)
    }

    fun saveMeasurementToDb(temperature: Float, humidity: Float, lightLevel: Float) {

    }
}