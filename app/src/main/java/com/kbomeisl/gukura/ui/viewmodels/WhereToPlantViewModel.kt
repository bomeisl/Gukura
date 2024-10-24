package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WhereToPlantViewModel(
    private val gardenRepository: GardenRepository,
    private val plantRepository: PlantRepository
): ViewModel() {
    private val coroutineScope = viewModelScope
    val recommendedGardenList = MutableStateFlow(listOf<GardenUi>())

    fun getRecommendedGardenList(plantName: String) {
        val plantDb = plantRepository.getPlantDb(plantName)
        coroutineScope.launch {
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

    fun getGardenList() {
        coroutineScope.launch {
            gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }


}