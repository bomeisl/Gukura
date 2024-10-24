package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WhereToPlantViewModel(
    private val gardenRepository: GardenRepository,
    private val plantRepository: PlantRepository
): ViewModel() {
    private val coroutineScope = viewModelScope
    val recommendedGardenList = MutableStateFlow(listOf<GardenUi>())
    val plantSearchList = MutableStateFlow(listOf<PlantUi>())
    val gardenList = MutableStateFlow(listOf<GardenUi>())

    fun getRecommendedGardenList(plantName: String) {
        coroutineScope.launch(Dispatchers.IO) {
        val plantDb = plantRepository.getPlantDb(plantName)
            recommendedGardenList.value = gardenRepository.getAllGardens()
                .filter {
                    plantDb.minTemperature < it.avgTemperature
                            && it.avgTemperature < plantDb.maxTemperature
                }
                .filter {
                    plantDb.minHumidity < it.avgHumidity
                            && it.avgHumidity < plantDb.maxHumidity
                }
                .filter {
                    plantDb.minLightLevel < it.avgLightLevel
                            && it.avgLightLevel < plantDb.maxLightLevel
                }
                .map { it.toUi() }
        }
    }

    fun getPlantsByName(plantName: String) {
        coroutineScope.launch {
            plantSearchList.value = plantRepository.getAllPlantsNetwork()
                .filter { it.name.startsWith(plantName, ignoreCase = true) }
                .map { it.toUi() }
        }
    }

//    fun getGardenList() {
//        viewModelScope.launch(Dispatchers.IO) {
//            plantSearchList.value = plantRepository.getAllPlantsDb()
//                .filter { it.garden ==  currentGarden.value.name }
//        }
//    }
}