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
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.testData.plants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val plantRepository: PlantRepository,
    private val gardenRepository: GardenRepository
): PlantViewModel(plantRepository, gardenRepository) {

    var recommendedPlants = listOf<PlantUi>()

    fun cacheAllPlants() {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.cacheAllPlants()
        }
    }
}