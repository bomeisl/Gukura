package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow

class LightEventListener: SensorEventListener {
    val light = MutableStateFlow<Float>(0f)

    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            Log.d("Light Sensor", "light event")
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                light.value = event.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
}