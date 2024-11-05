package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow

class HumidityEventListener: SensorEventListener {
    val humidity = MutableStateFlow<Float>(0f)
    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            Log.d("Humidity event", "humidity sensor")
            if (event.sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY) {
                humidity.value = event.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
}