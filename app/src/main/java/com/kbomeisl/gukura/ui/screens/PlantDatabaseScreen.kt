package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantDatabaseScreen(
    findAPlantViewModel: FindAPlantViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    var plantName = remember { mutableStateOf("") }
    val plantList = findAPlantViewModel.plantList.collectAsState()
    val gardenList = findAPlantViewModel.gardenList.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(plantName.value) {
        findAPlantViewModel.getPlantsByName(plantName.value)
    }

    Surface() {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(120.dp))
            Text("Look Up a Plant", fontFamily = FontFamily.Monospace)
            TextField(
                value = plantName.value,
                onValueChange = { plantName.value = it}
            )
            Column(Modifier.verticalScroll(scrollState)) {
                plantList.value.forEach {
                    PlantCard(
                        it,
                        snackbarHostState = snackbarHostState,
                        gardenList = gardenList.value
                    )
                }
            }
        }
    }

}