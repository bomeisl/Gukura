package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantRecommendationScreen(
    measurementViewModel: MeasurementViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val plantList = measurementViewModel.plantList.collectAsStateWithLifecycle()

    Surface(Modifier.fillMaxSize()){
        Row{
            Column {
                Spacer(Modifier.height(100.dp))
                plantList.value.forEach {
                    //PlantCard(it)
                }
            }
        }
    }
}