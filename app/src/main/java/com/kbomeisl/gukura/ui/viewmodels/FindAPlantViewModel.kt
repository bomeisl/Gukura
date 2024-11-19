package com.kbomeisl.gukura.ui.viewmodels

import android.hardware.SensorManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.context.GlobalContext.get

class FindAPlantViewModel(): PlantViewModel() {
    var plantLifeType = mutableStateOf("")
    var plantFlowerType = mutableStateOf("")
    var plantSizeType = mutableStateOf("")

    fun getPlantFromDatabase(plantName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            plantRepository.getPlantDb(plantName = plantName)
        }
    }

    fun saveMeasurementToDb(temperature: Float, humidity: Float, lightLevel: Float) {

    }

}