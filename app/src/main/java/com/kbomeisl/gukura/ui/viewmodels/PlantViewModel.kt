package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.gardens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class PlantViewModel(
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): ViewModel() {
    val coroutineScope = viewModelScope
    val plantList = MutableStateFlow(listOf<PlantUi>())
    val gardenList = MutableStateFlow(gardens.gardenList)
    val recommendedPlants = MutableStateFlow(listOf<PlantUi>())
    val currentGarden = MutableStateFlow(GardenUi())
    val wishListPlants = MutableStateFlow(listOf<PlantUi>())

    fun getPlantsDb() {
        coroutineScope.launch(Dispatchers.IO) {
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

    fun populatePlantList(
        temperature: Float,
        humidity: Float,
        lightLevel: Float,
        location: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            plantList.value =
                plantRepository.getPlantsInRange(temperature, humidity, lightLevel)
        }
    }

    fun populateGardenList() {
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }

    fun getPlantsInGarden(gardenName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            plantList.value = plantRepository.getAllPlantsDb()
                .filter { it.gardenName ==  gardenName}
        }
    }

    fun getMyPlants() {
        coroutineScope.launch(Dispatchers.IO) {
            wishListPlants.value = plantRepository.getAllPlantsDb()
                .filter { it.wishListed == true }
        }
    }

    fun getRecommendedGardenList(plantName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            val plantDb = plantRepository.getPlantDb(plantName)
            gardenList.value = gardenRepository.getAllGardens()
                .filter {
                    plantDb.minTemperature.toFloat() < it.avgTemperature.toFloat()
                            && it.avgTemperature.toFloat() < plantDb.maxTemperature.toFloat()
                }
                .filter {
                    plantDb.minHumidity.toFloat() < it.avgHumidity.toFloat()
                            && it.avgHumidity .toFloat()< plantDb.maxHumidity.toFloat()
                }
                .filter {
                    plantDb.minLightLevel.toFloat() < it.avgLightLevel.toFloat()
                            && it.avgLightLevel.toFloat() < plantDb.maxLightLevel.toFloat()
                }
                .map { it.toUi() }
        }
    }

    fun addGardenToPlant(plantUi: PlantUi, gardenDb: GardenDb) {
        coroutineScope.launch(Dispatchers.IO) {
            val plantDb = plantRepository.getPlantDb(plantUi.name)
            plantRepository.addGarden(plantDb = plantDb, gardenDb = gardenDb)
        }
    }

    fun removeGardenFromPlant(plantUi: PlantUi, gardenName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            val plantDb = plantRepository.getPlantDb(plantUi.name)
            plantRepository.deleteGarden(plantDb)
        }
    }

    fun updateCurrentGarden(newGarden: GardenUi) {
        currentGarden.value = newGarden
    }


}