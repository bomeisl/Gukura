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
import com.kbomeisl.gukura.data.sensor.GeomagneticEventListener
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
    lateinit var geomagneticEventListener: GeomagneticEventListener
    var temperatureSensor: Sensor? = null
    var humiditySensor: Sensor? = null
    var lightSensor: Sensor? = null
    var geomagneticSensor: Sensor? = null
    lateinit var sensorList: List<Sensor>
    val samplingIntervalMs = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        temperatureEventListener = TemperatureEventListener()
        humidityEventListener = HumidityEventListener()
        lightEventListener = LightEventListener()
        geomagneticEventListener = GeomagneticEventListener()
        initializeLightSensor()
        initializeHumiditySensor()
        initializeTemperatureSensor()
        initializeGeomagneticSensor()
        this.lifecycleScope.launch { plantViewModel.initialPlantCaching() }
        lifecycle.addObserver(plantViewModel)
        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                GukuraBaseScreen(
                    temperature = temperatureEventListener.temperature,
                    humidity = humidityEventListener.humidity,
                    lightLevel = lightEventListener.light,
                    geomagneticX = geomagneticEventListener.geomagneticFieldX,
                    geomagneticY = geomagneticEventListener.geomagneticFieldY,
                    geomagneticZ = geomagneticEventListener.geomagneticFieldZ
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
                samplingIntervalMs
            )
        }
        humiditySensor?.also {
            sensorManager.registerListener(
                humidityEventListener,
                humiditySensor,
                samplingIntervalMs,
            )
        }
        lightSensor?.also {
            sensorManager.registerListener(
                lightEventListener,
                lightSensor,
                samplingIntervalMs
            )
        }
        geomagneticSensor?.also {
            sensorManager.registerListener(
                geomagneticEventListener,
                geomagneticSensor,
                samplingIntervalMs
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(temperatureEventListener)
        sensorManager.unregisterListener(humidityEventListener)
        sensorManager.unregisterListener(lightEventListener)
        sensorManager.unregisterListener(geomagneticEventListener)
    }

    fun initializeTemperatureSensor() {
        val temperatureSensors = listOf(65538,65539,Sensor.TYPE_AMBIENT_TEMPERATURE, Sensor.TYPE_TEMPERATURE)
        temperatureSensors.forEach {
            if (sensorList.map { it.type }.contains(it)) {
                temperatureSensor = sensorManager.getDefaultSensor(it)
            }
        }
    }

    fun initializeHumiditySensor() {
        if (sensorList.map { it.type }.contains(Sensor.TYPE_RELATIVE_HUMIDITY)) {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        }
    }

    fun initializeLightSensor() {
        if (sensorList.map { it.type }.contains(Sensor.TYPE_LIGHT)) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        }
    }

    fun initializeGeomagneticSensor() {
        if (sensorList.map { it.type }.contains(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)) {
            geomagneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        }
    }
}