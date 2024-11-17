package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlinx.coroutines.flow.MutableStateFlow

class TemperatureEventListener: SensorEventListener {
    val temperature = MutableStateFlow(0f)

    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            temperature.value = event.values[0]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
}