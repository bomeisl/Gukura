package com.kbomeisl.gukura.ui.viewmodels

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.sensor.SensorDataSource
import kotlinx.coroutines.launch

class FindAPlantViewModel(
    private val findAPlantRepository: FindAPlantRepository,
    private val sensorDataSource: SensorDataSource
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


    fun getPlantFromFirebase(plantName: String) {
        coroutineScope.launch {
            //check if the plant is in the online database
            findAPlantRepository.lookUpPlantApi(plantName = plantName)
        }
    }
}