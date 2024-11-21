package com.kbomeisl.gukura.ui.viewmodels

import android.hardware.Sensor
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.MeasurementDb
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.LocationRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.WeatherRepository
import com.kbomeisl.gukura.data.sensor.CompassEventListener
import com.kbomeisl.gukura.data.sensor.LightEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MeasurementViewModel(
    private val measurementDao: MeasurementDao,
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
): PlantViewModel() {
    val logTag = "Measurement View Model"
    val outsideHumidity = MutableStateFlow(0)
    val outsideTemperature = MutableStateFlow(0f)
    override val coroutineScope = viewModelScope

    lateinit var lightEventListener: LightEventListener
    lateinit var compassEventListener: CompassEventListener
    var lightSensor: Sensor? = null
    lateinit var sensorList: List<Sensor>
    val samplingIntervalMs = 5000
    var geomagneticSensor: Sensor? = null
    var accelerometerSensor: Sensor? = null
    var rotationMatrix = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f,0f)
    var inclinationMatrix = floatArrayOf(0f,0f,0f,0f,0f,0f,0f,0f,0f)
    var magneticOrientation = floatArrayOf(0f,0f,0f)
    val magneticOrientationX = MutableStateFlow(magneticOrientation[1])
    val heading = locationRepository.heading

    init {
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        //bootstrap light sensor systems
        initializeLightSensor()
        lightEventListener = LightEventListener()
        lightSensor?.also { sensorManager.registerListener(lightEventListener, it, samplingIntervalMs) }
        //bootstrap compass sensor systems
        initializeCompassSensor()
        compassEventListener = CompassEventListener()
        locationRepository.initializeLocationServices()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d(logTag, "OnResume")
        lightSensor?.also { sensorManager.registerListener(lightEventListener, it, samplingIntervalMs) }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        lightSensor?.let { sensorManager.unregisterListener(lightEventListener) }
        locationRepository.unRegisterLocationListener()
    }

    fun saveMeasurementToDb(
        temperature: Float,
        humidity: Float,
        lightLevel: Float,
        gardenName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            measurementDao.upsertMeasurement(
                MeasurementDb(
                temperature = temperature,
                humidity = humidity,
                lightLevel = lightLevel,
                garden = gardenName,
                timestamp = Timestamp.now().toString()
                )
            )
            gardenRepository.upsertGarden(
                GardenDb(
                    name = gardenName,
                    gardenId = gardenName.hashCode(),
                    avgTemperature = temperature.toString(),
                    avgHumidity = humidity.toString(),
                    avgLightLevel = lightLevel.toString()
                )
            )
        }
    }

    fun getHumidity() {
        coroutineScope.launch(Dispatchers.IO) {
            val weather = weatherRepository.getWeather()
            outsideHumidity.value = weather.current.humidity
        }
    }

    fun getTemperature() {
        coroutineScope.launch(Dispatchers.IO) {
            val weather = weatherRepository.getWeather()
            outsideTemperature.value = weather.current.temp_f
        }
    }

    fun initializeLightSensor() {
        if (sensorList.map { it.type }.contains(Sensor.TYPE_LIGHT)) {
            Log.d(logTag, "Initialize light sensor")
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        }
    }

    fun initializeCompassSensor() {
        if (
            sensorList.map { it.type }.contains(Sensor.TYPE_ACCELEROMETER)
            &&
            sensorList.map { it.type }.contains(Sensor.TYPE_MAGNETIC_FIELD)
            ) {
            geomagneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }
    }

    fun initializeCompassEventListener() {
        compassEventListener = CompassEventListener()
    }

    fun initializeLightMeasurements() {
        lightSensor?.also { sensorManager.registerListener(lightEventListener, it, samplingIntervalMs) }
    }

    fun saveGardenMeasurements(
        gardenName: String,
        lightLevel: Float,
        compassDirection: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            val garden = gardenRepository.getGardenByName(gardenName = gardenName)
            gardenRepository.upsertGarden(
                GardenDb(
                    gardenId = garden.gardenId,
                    name = garden.name,
                    avgTemperature = garden.avgTemperature,
                    avgHumidity = garden.avgHumidity,
                    avgLightLevel = lightLevel.toString(),
                    windowDirection = compassDirection
                )
            )
        }
    }
}