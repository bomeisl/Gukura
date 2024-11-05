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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class MainActivity() : ComponentActivity() {
    private val logtag = "Main Activity"
    lateinit var sensorManager: SensorManager
    private val plantViewModel: PlantViewModel = get<PlantViewModel>()
    lateinit var temperatureEventListener: TemperatureEventListener
    lateinit var humidityEventListener: HumidityEventListener
    lateinit var lightEventListener: LightEventListener
    var temperatureSensor1: Sensor? = null
    var temperatureSensor: Sensor? = null
    var humiditySensor: Sensor? = null
    var lightSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperatureEventListener = TemperatureEventListener()
        humidityEventListener = HumidityEventListener()
        lightEventListener = LightEventListener()
        temperatureSensor1 = sensorManager.getDefaultSensor(65538)
        temperatureSensor = sensorManager.getDefaultSensor(65539)
        Log.d(logtag, "Temperature Sensors: ${sensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE)}")
        Log.d(logtag, "Humidity Sensors: ${sensorManager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY)}")
        Log.d(logtag, "Light Sensors: ${sensorManager.getSensorList(Sensor.TYPE_LIGHT)}")
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        this.lifecycleScope.launch { plantViewModel.initialPlantCaching() }
        lifecycle.addObserver(plantViewModel)
        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                GukuraBaseScreen(
                    temperature = temperatureEventListener.temperature,
                    humidity = humidityEventListener.humidity,
                    lightLevel = lightEventListener.light
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        temperatureSensor?.also {
            sensorManager.registerListener(
                temperatureEventListener,
                temperatureSensor,
                Sensor.TYPE_AMBIENT_TEMPERATURE
            )
        }
        humiditySensor?.also {
            sensorManager.registerListener(
                humidityEventListener,
                humiditySensor,
                Sensor.TYPE_RELATIVE_HUMIDITY
            )
        }
        lightSensor?.also {
            sensorManager.registerListener(
                lightEventListener,
                lightSensor,
                Sensor.TYPE_LIGHT
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(temperatureEventListener)
        sensorManager.unregisterListener(humidityEventListener)
        sensorManager.unregisterListener(lightEventListener)
    }
}