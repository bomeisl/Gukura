package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.navigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.testData.plants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): PlantViewModel() {
    val currentGarden = MutableStateFlow(GardenUi())
    var recommendedPlants = listOf<PlantUi>()
    val gardenPlantList = MutableStateFlow(listOf<PlantUi>())

    fun cacheAllPlants() {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.cacheAllPlants()
        }
    }

    fun getGardens() {
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens().map { it.toUi() }
        }
    }

    fun getPlantsInGarden(gardenName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            gardenPlantList.value = plantRepository.getAllPlantsDb().map { it.toUi() }
                .filter { it.gardenName ==  gardenName}
        }
    }
}