package com.kbomeisl.gukura.ui.viewmodels

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.toUi
import com.kbomeisl.gukura.data.network.PlantNetwork
import com.kbomeisl.gukura.data.network.toUi
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.sensor.SensorDataSource
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FindAPlantViewModel(
    private val findAPlantRepository: FindAPlantRepository,
    private val sensorDataSource: SensorDataSource,
    private val plantDao: PlantDao
): ViewModel(), DefaultLifecycleObserver {
    private val coroutineScope = viewModelScope
    val temperature = sensorDataSource.temperature
    val humidity = sensorDataSource.humidity
    val lightLevel = sensorDataSource.light

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        sensorDataSource.initializeSensors()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        sensorDataSource.attachListeners()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        sensorDataSource.detatchListener()
    }

    fun getPlantFromFirebase(plantName: String): Deferred<PlantUi> {
        val plantNetworkJob = coroutineScope.async {
           findAPlantRepository.lookUpPlantApi(plantName = plantName).toUi()
        }
        return plantNetworkJob
    }

    fun getPlantFromDatabase(plantName: String): PlantUi {
        return plantDao.findPlant(plantName = plantName).toUi()
    }
}