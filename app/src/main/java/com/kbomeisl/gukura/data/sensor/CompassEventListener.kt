package com.kbomeisl.gukura.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.google.android.gms.location.DeviceOrientation
import com.google.android.gms.location.DeviceOrientationListener
import com.google.android.gms.location.DeviceOrientationRequest
import kotlinx.coroutines.flow.MutableStateFlow

class CompassEventListener: DeviceOrientationListener {
    val headingDegrees = MutableStateFlow(0f)

    override fun onDeviceOrientationChanged(p0: DeviceOrientation) {
        headingDegrees.value = p0.headingDegrees
    }
}