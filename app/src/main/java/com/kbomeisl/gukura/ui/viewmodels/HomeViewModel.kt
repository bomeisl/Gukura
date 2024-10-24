package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.ui.models.navigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.testData.plants

class HomeViewModel(
    val homeRepository: HomeRepository
): ViewModel() {
    var recommendedPlantList = listOf<PlantUi>()
    var myGardenList = gardens.gardenList


    fun getRecommendedPlants(): List<PlantUi> {
        return listOf<PlantUi>()
    }
}