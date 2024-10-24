package com.kbomeisl.gukura.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
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
    val recommendedPlants = MutableStateFlow<List<PlantUi>>(listOf())
    var plantLifeType = mutableStateOf("")
    var plantFlowerType = mutableStateOf("")
    var plantSizeType = mutableStateOf("")
    var garden = mutableStateOf<GardenUi>(GardenUi())

    fun populatePlantList() {
        coroutineScope.launch {
            plantList.value = plantRepository.getAllPlantsNetwork().map { it.toUi() }
        }
    }

    fun getPlantsByName(plantName: String) {
        coroutineScope.launch {
            plantList.value = plantRepository.getAllPlantsNetwork()
                .filter { it.name.startsWith(plantName, ignoreCase = true) }
                .map { it.toUi() }
        }
    }

    fun getPlantsInRange(temperature: Float,humidity: Float,lightLevel: Float) {
        coroutineScope.launch {
            recommendedPlants.value = plantRepository.getPlantsInRange(temperature, humidity, lightLevel)
        }
    }

    fun getPlantFromDatabase(plantName: String): PlantDb {
        return plantRepository.getPlantDb(plantName = plantName)
    }

    fun saveMeasurementToDb(temperature: Float, humidity: Float, lightLevel: Float) {

    }
}