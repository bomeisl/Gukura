package com.kbomeisl.gukura.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun SunlightSelectionScreen(
    lightLevel: MutableStateFlow<Float>,
    navHostController: NavHostController,
    measurementViewModel: MeasurementViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    gardenName: String = ""
) {

}