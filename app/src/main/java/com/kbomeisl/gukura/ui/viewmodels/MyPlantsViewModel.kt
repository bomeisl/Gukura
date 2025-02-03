package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyPlantsViewModel(
    private val plantRepository: PlantRepository
): ViewModel() {
    val coroutineScope = viewModelScope
    val wishListPlants = MutableStateFlow(listOf<PlantUi>())
    val plantList = MutableStateFlow(listOf<PlantUi>())

    fun getMyPlants() {
        coroutineScope.launch {
            wishListPlants.value = plantRepository.getAllPlantsDb().map { it.toUi() }
                .filter { it.wishListed == true }
        }
    }


}