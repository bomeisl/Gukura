package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WishListViewModel(
): PlantViewModel() {
    val wishlistedPlants = MutableStateFlow(listOf<PlantUi>())

    fun getWishlistedPlants() {
        viewModelScope.launch(Dispatchers.IO) {
            wishlistedPlants.value = plantRepository.getAllPlantsDb()
                .filter { it.wishListed }
                .map { it.toUi() }
        }
    }
}