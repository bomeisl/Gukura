package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlinx.coroutines.flow.MutableStateFlow

class GeomagneticEventListener: SensorEventListener {
    val geomagneticFieldX = MutableStateFlow(0f)
    val geomagneticFieldY = MutableStateFlow(0f)
    val geomagneticFieldZ = MutableStateFlow(0f)
    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                geomagneticFieldX.value = event.values[0]
                geomagneticFieldY.value = event.values[1]
                geomagneticFieldZ.value = event.values[2]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
}