package com.kbomeisl.gukura.data.sensor

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow

class SensorDataSource(val context: Context): SensorEventListener, DefaultLifecycleObserver {
    private lateinit var sensorManager: SensorManager
    private var temperatureSensor: Sensor? = null
    private var humiditySensor: Sensor? = null
    private var lightSensor: Sensor? = null
    val temperature = MutableStateFlow<Float>(0F)
    val humidity = MutableStateFlow<Float>(0F)
    val light = MutableStateFlow<Float>(0F)

    fun initializeSensors() {
        sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    fun attachListeners() {
        if (temperatureSensor != null) {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (humiditySensor != null) {
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    //sensor lifecycle methods
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            when (event.sensor.type) {
                Sensor.TYPE_AMBIENT_TEMPERATURE -> temperature.value = event.values[0]
                Sensor.TYPE_RELATIVE_HUMIDITY -> humidity.value = event.values[0]
                Sensor.TYPE_LIGHT -> light.value = event.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    //activity lifecycle methods
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        initializeSensors()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        attachListeners()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        sensorManager.unregisterListener(this)
    }

}