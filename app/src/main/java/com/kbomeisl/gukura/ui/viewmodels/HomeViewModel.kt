package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.navigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.testData.plants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): ViewModel() {

    val coroutineScope = viewModelScope
    var recommendedPlantList = listOf<PlantUi>()
    var gardenList = MutableStateFlow(listOf<GardenUi>())
    val currentGarden = MutableStateFlow(GardenUi())
    val plantList = MutableStateFlow(listOf<PlantUi>())

    fun getRecommendedPlants(): List<PlantUi> {
        return listOf<PlantUi>()
    }

    fun getPlantsInGarden() {
        viewModelScope.launch(Dispatchers.IO) {
            plantList.value = plantRepository.getAllPlantsDb()
                .filter { it.garden ==  currentGarden.value.name}
        }
    }

    fun populateGardenList() {
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }

    fun cacheAllPlants() {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.cacheAllPlants()
        }
    }
}