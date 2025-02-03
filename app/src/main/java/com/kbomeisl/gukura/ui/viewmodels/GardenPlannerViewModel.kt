package com.kbomeisl.gukura.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.ui.models.toDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GardenPlannerViewModel(): PlantViewModel() {
    override val logTag = "Garden View Model"
    override val coroutineScope = viewModelScope
    val plantListDb = MutableStateFlow(listOf<PlantDb>())
    val filteredList = MutableStateFlow(listOf<PlantDb>())

    init {
        populateGardenList()
    }

    fun getPlantsFromDb() {
        coroutineScope.launch(Dispatchers.IO) {
            plantListDb.value = plantRepository.getAllPlantsDb()
        }
    }

    fun filterPlantsBySunlight(
        sunlight: Float,
    ) {
        filteredList.value = plantListDb.value.filter {
            Log.d(logTag, it.minLightLevel.toString())
            Log.d(logTag, it.maxLightLevel.toString())
            sunlight >= it.minLightLevel
                    &&
            sunlight <= it.maxLightLevel
        }
    }

    fun filterPlantsByCompassHeading(
        direction: String
    ): List<PlantDb> {
        return plantList.value.map{it.toDb() }.filter {
            it.directions.split(",").contains(direction)
        }
    }

    fun getRecommendedPlants(
        sunlight: Float,
        heading: Float
    ) {
        getPlantsFromDb()
        filterPlantsBySunlight(sunlight = sunlight)
    }
}
