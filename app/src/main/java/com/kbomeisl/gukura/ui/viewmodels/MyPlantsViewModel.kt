package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.MyPlantsRepository
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyPlantsViewModel(
    private val myPlantsRepository: MyPlantsRepository
): ViewModel() {
    val coroutineScope = viewModelScope
    //mocked up data simulating a user's houseplants
    val usersPlants = listOf<String>(
        "Begonia",
        "African Violet",
        "Air Plants",
        "Hoya"
    )
    val plantList = MutableStateFlow(listOf<PlantUi>())

    fun getPlants() {
        coroutineScope.launch {
            plantList.value = myPlantsRepository.getPlants(usersPlants).map { it.toUi() }
            Log.d("View Model", "couroutine launch")
        }
    }


}