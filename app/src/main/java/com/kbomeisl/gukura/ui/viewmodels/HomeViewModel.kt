package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.ui.models.navigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.PlantUi

class HomeViewModel(
    val homeRepository: HomeRepository
): ViewModel(), DefaultLifecycleObserver {
    val homeTopAppBar: GukuraTopAppBar = GukuraTopAppBar()
    var myPlantList = listOf<PlantUi>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        myPlantList = homeRepository.getMyPlants().map {
            plantDb ->  plantDb.toUi()
        }
    }
}