package com.kbomeisl.gukura.data.repository

import android.content.Context
import android.util.Log
import com.google.android.gms.location.DeviceOrientation
import com.google.android.gms.location.DeviceOrientationListener
import com.google.android.gms.location.DeviceOrientationRequest
import com.google.android.gms.location.FusedOrientationProviderClient
import com.google.android.gms.location.LocationServices
import com.kbomeisl.gukura.GukuraApplication
import com.kbomeisl.gukura.MainActivity
import com.kbomeisl.gukura.data.sensor.CompassEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Executors

class LocationRepository(private val context: Context) {
    val logTag = "Location Repository"
    val heading = MutableStateFlow(0f)

    private val headinglistener: DeviceOrientationListener =
        DeviceOrientationListener {
            deviceOrientation: DeviceOrientation ->
            heading.value = deviceOrientation.headingDegrees
        }

    private val fusedOrientationProviderClient: FusedOrientationProviderClient =
        LocationServices.getFusedOrientationProviderClient(context)

    fun initializeLocationServices() {
        Log.d(logTag, "initialization")
        val request = DeviceOrientationRequest.Builder(DeviceOrientationRequest.OUTPUT_PERIOD_DEFAULT).build()
        val executor = Executors.newSingleThreadScheduledExecutor()
        fusedOrientationProviderClient
            .requestOrientationUpdates(request,executor,headinglistener)
            .addOnSuccessListener { Log.d(logTag, "registered") }
            .addOnFailureListener { Log.d(logTag, "Failed to register") }
    }

    fun unRegisterLocationListener() {
        fusedOrientationProviderClient.removeOrientationUpdates(headinglistener)
    }

}