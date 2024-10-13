package com.kbomeisl.gukura

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.kbomeisl.gukura.ui.screens.GukuraBaseScreen
import com.kbomeisl.gukura.ui.theme.GukuraTheme

class MainActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                GukuraBaseScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onSensorChanged(event: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}