package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.common.GeomagneticSensorCard
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.common.HumiditySensorCard
import com.kbomeisl.gukura.ui.common.SensorCard
import com.kbomeisl.gukura.ui.common.SunlightSensorCard
import com.kbomeisl.gukura.ui.common.TemperatureSensorCard
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MeasurementScreen(
    navHostController: NavHostController,
    measurementViewModel: MeasurementViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    gardenName: String = ""
) {
    LaunchedEffect(Unit) {
        measurementViewModel.initializeLightMeasurements()
        measurementViewModel.populateGardenList()
    }

    Column(
        Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SunlightSensorCard(
            lightLevel = measurementViewModel.lightEventListener.light
        )

//        GeomagneticSensorCard(
//            magneticOrientation = magneticOrientation
//        )

    }
}
