package com.kbomeisl.gukura.ui.viewmodels

import android.hardware.SensorManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.sensor.CompassHeading
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

open class PlantViewModel(
    val plantRepository: PlantRepository = get(PlantRepository::class.java),
    private val gardenRepository: GardenRepository = get(GardenRepository::class.java),
    val sensorManager: SensorManager = get(SensorManager::class.java)
): ViewModel(), DefaultLifecycleObserver {
    open val coroutineScope = viewModelScope
    open val plantList = MutableStateFlow(listOf<PlantUi>())
    val gardenList = MutableStateFlow(listOf<GardenUi>())
    val recommendedPlantList = MutableStateFlow(listOf<PlantUi>())
    val currentGarden = MutableStateFlow(GardenUi())
    val wishListPlants = MutableStateFlow(listOf<PlantUi>())
    val gardenPlantList = MutableStateFlow(listOf<PlantUi>())


    fun initialPlantCaching() {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.cacheAllPlants()
        }
    }

    fun convertCompassHeadingToDirection(heading: Float): String? {
        if (heading <= CompassHeading.EAST.max
            &&
            heading >= CompassHeading.EAST.min
        ) {
            return CompassHeading.EAST.dir
        }
        if (heading <= CompassHeading.WEST.max
            &&
            heading >= CompassHeading.WEST.min
        ) {
            return CompassHeading.WEST.dir
        }
        if (heading <= CompassHeading.SOUTH.max
            &&
            heading >= CompassHeading.SOUTH.min
        ) {
            return CompassHeading.SOUTH.dir
        }
        if (heading >= CompassHeading.NORTH.min
            &&
            heading <= CompassHeading.NORTH.max
        ) {
            return CompassHeading.NORTH.dir
        }
        if (heading >= CompassHeading.NORTH2.min
            &&
            heading <= CompassHeading.NORTH2.max
        ) {
            return CompassHeading.NORTH.dir
        } else {
            return null
        }
    }

    open fun getAllPlantsForScreen() {
        coroutineScope.launch(Dispatchers.IO) {
            plantList.value = plantRepository.getAllPlantsDb().map { it.toUi() }
        }
    }

    fun getPlantsDb() {
        coroutineScope.launch(Dispatchers.IO) {
            plantList.value = plantRepository.getAllPlantsDb().map { it.toUi() }
        }
    }

    fun getPlantsByName(plantName: String) {
        coroutineScope.launch {
            plantList.value = plantRepository.getAllPlantsNetwork()
                .filter { it.name?.startsWith(plantName, ignoreCase = true) ?: false }
                .map { it.toUi() }
        }
    }

    fun getPlantsInRange(temperature: Float,humidity: Float,lightLevel: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            val plants = plantRepository.getPlantsInRange(temperature, humidity, lightLevel)
            plants.forEach {
                it.gardenTemp = temperature.toString()
                it.gardenHumidity = humidity.toString()
                it.gardenLightLevel = lightLevel.toString()
            }
            recommendedPlantList.value = plants
            plantList.value = plantRepository.getAllPlantsDb().map { it.toUi() }
            gardenList.value = gardenRepository.getAllGardens().map { it.toUi() }
        }
    }

    fun populatePlantList(
        temperature: Float,
        humidity: Float,
        lightLevel: Float,
        location: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            plantList.value =
                plantRepository.getPlantsInRange(temperature, humidity, lightLevel)
        }
    }

    fun populateGardenList() {
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens()
                .map { it.toUi() }
        }
    }

    fun getMyPlants() {
        coroutineScope.launch(Dispatchers.IO) {
            wishListPlants.value = plantRepository.getAllPlantsDb().map { it.toUi() }
                .filter { it.wishListed }
        }
    }

    fun getRecommendedGardenList(plantName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            val plantDb = plantRepository.getPlantDb(plantName)
            gardenList.value = gardenRepository.getAllGardens()
                .filter {
                    plantDb.minTemperature.toFloat() < it.avgTemperature.toFloat()
                            && it.avgTemperature.toFloat() < plantDb.maxTemperature.toFloat()
                }
                .filter {
                    plantDb.minHumidity.toFloat() < it.avgHumidity.toFloat()
                            && it.avgHumidity .toFloat()< plantDb.maxHumidity.toFloat()
                }
                .filter {
                    plantDb.minLightLevel.toFloat() < it.avgLightLevel.toFloat()
                            && it.avgLightLevel.toFloat() < plantDb.maxLightLevel.toFloat()
                }
                .map { it.toUi() }
        }
    }

    fun addGardenToPlant(plantUi: PlantUi, gardenDb: GardenDb) {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.addGarden(plantDb = plantUi.toDb(), gardenDb = gardenDb)
        }
    }

    fun removeGardenFromPlant(plantUi: PlantUi, gardenName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            val plantDb = plantRepository.getPlantDb(plantUi.name)
            plantRepository.deleteGarden(plantDb)
        }
    }

    fun updateCurrentGarden(newGarden: GardenUi) {
        currentGarden.value = newGarden
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens().map { it.toUi() }
        }
    }

    fun addGarden(gardenDb: GardenDb) {
        coroutineScope.launch(Dispatchers.IO) {
            gardenRepository.upsertGarden(gardenDb = gardenDb)
            gardenList.value = gardenRepository.getAllGardens().map { it.toUi() }
        }

    }

    fun getGardens() {
        coroutineScope.launch(Dispatchers.IO) {
            gardenList.value = gardenRepository.getAllGardens().map { it.toUi() }
        }
    }

    fun deleteGarden(gardenUi: GardenUi) {
        coroutineScope.launch(Dispatchers.IO) {
            gardenRepository.deleteGarden(gardenDb = gardenUi.toDb())
            val plantsInGarden = plantList.value.filter { it.gardenName == gardenUi.name }
            plantsInGarden.forEach { removeGardenFromPlant(it, gardenName = gardenUi.name) }
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