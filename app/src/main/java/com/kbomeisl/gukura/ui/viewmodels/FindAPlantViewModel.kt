package com.kbomeisl.gukura.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FindAPlantViewModel(
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): ViewModel() {
    private val coroutineScope = viewModelScope

    val plantList = MutableStateFlow(listOf<PlantUi>())
    val recommendedPlants = MutableStateFlow(listOf<PlantUi>())
    var plantLifeType = mutableStateOf("")
    var plantFlowerType = mutableStateOf("")
    var plantSizeType = mutableStateOf("")
    var garden = mutableStateOf(GardenUi())
    val gardenList = MutableStateFlow(gardens.gardenList)

    fun getPlantsDb() {
        coroutineScope.launch() {
            plantList.value = plantRepository.getAllPlantsDb()
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

    fun getPlantFromDatabase(plantName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.getPlantDb(plantName = plantName)
        }
    }

    fun saveMeasurementToDb(temperature: Float, humidity: Float, lightLevel: Float) {

    }

    fun populateGardenList() {
        coroutineScope.launch {
            gardenList.value = gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }

    fun assignGarden(garden: String, plantUi: PlantUi) {
        val newPlant = PlantUi(
            name = plantUi.name,
            description = plantUi.description,
            temperature = plantUi.temperature,
            humidity = plantUi.humidity,
            lightLevel = plantUi.lightLevel,
            imageUrl = plantUi.imageUrl,
            garden = garden,
            wishListed = plantUi.wishListed
        )
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.upsertPlant(newPlant.toDb())
        }
    }

    fun removeGarden(plantUi: PlantUi, garden: String) {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.deleteGarden(plantDb = plantUi.toDb())
        }
    }
}