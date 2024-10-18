package com.kbomeisl.gukura

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kbomeisl.gukura.ui.screens.FindAPlant
import com.kbomeisl.gukura.ui.screens.Home
import com.kbomeisl.gukura.ui.theme.GukuraTheme
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : ComponentActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Main Activity","onCreate")

        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                Home()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("Main Activity", "sensor changed")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}