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
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.sensor.HumidityEventListener
import com.kbomeisl.gukura.data.sensor.LightEventListener
import com.kbomeisl.gukura.data.sensor.TemperatureEventListener
import com.kbomeisl.gukura.data.sensor.sensorDataSource
import com.kbomeisl.gukura.data.sensor.sensorDataSource.humiditySensor
import com.kbomeisl.gukura.data.sensor.sensorDataSource.lightSensor
import com.kbomeisl.gukura.data.sensor.sensorDataSource.temperatureSensor
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.theme.GukuraTheme
import com.kbomeisl.gukura.ui.viewmodels.PlantViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : ComponentActivity() {
    private val logtag = "Main Activity"
    private val gardenRepository = get<GardenRepository>()
    private val plantRepository = get<PlantRepository>()
    private val sensorManager = get<SensorManager>()
    private val plantViewModel: PlantViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.lifecycleScope.launch { plantViewModel.initialPlantCaching() }
        lifecycle.addObserver(plantViewModel)
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

    override fun onPause() {
        super.onPause()
    }

}