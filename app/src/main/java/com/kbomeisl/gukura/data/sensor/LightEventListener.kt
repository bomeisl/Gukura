package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import kotlinx.coroutines.flow.MutableStateFlow

class LightEventListener: SensorEventListener {
    val light = MutableStateFlow(0f)
    val logTag = "Light Event Listener"

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d(logTag, "Sensor Event Detected")
        event?.also {
            Log.d(logTag, "light event")
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                light.value = event.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
}