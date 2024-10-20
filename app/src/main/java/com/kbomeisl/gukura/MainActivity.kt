package com.kbomeisl.gukura

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kbomeisl.gukura.data.sensor.sensorDataSource
import com.kbomeisl.gukura.data.sensor.sensorDataSource.humiditySensor
import com.kbomeisl.gukura.data.sensor.sensorDataSource.lightSensor
import com.kbomeisl.gukura.data.sensor.sensorDataSource.temperatureSensor
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.screens.GukuraNavHost
import com.kbomeisl.gukura.ui.theme.GukuraTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity() : ComponentActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    private val temperature = MutableStateFlow<Float>(0F)
    private val humidity = MutableStateFlow<Float>(0F)
    private val lightLevel = MutableStateFlow<Float>(0F)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                GukuraBaseScreen(
                    temperature = temperature,
                    humidity = humidity,
                    lightLevel = lightLevel
                )
            }
        }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorDataSource.initializeSensors(sensorManager)
    }

    override fun onResume() {
        super.onResume()
        registerSensorListener()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("Main Activity", "sensor changed")
        event?.also {
            when (event.sensor.type) {
                Sensor.TYPE_AMBIENT_TEMPERATURE -> temperature.value = event.values[0]
                Sensor.TYPE_RELATIVE_HUMIDITY -> humidity.value = event.values[0]
                Sensor.TYPE_LIGHT -> lightLevel.value = event.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        val dummyVar = "something"
    }

    fun registerSensorListener() {
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
}