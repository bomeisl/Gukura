package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow

object sensorDataSource {
    var temperatureSensor: Sensor? = null
    var humiditySensor: Sensor? = null
    var lightSensor: Sensor? = null
    val temperature = MutableStateFlow<Float>(0F)
    val humidity = MutableStateFlow<Float>(0F)
    val light = MutableStateFlow<Float>(0F)

    fun initializeSensors(sensorManager: SensorManager) {
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }



}