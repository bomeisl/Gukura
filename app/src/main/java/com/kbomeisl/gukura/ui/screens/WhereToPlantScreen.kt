package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.viewmodels.WhereToPlantViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WhereToPlantScreen(
    whereToPlantViewModel: WhereToPlantViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val plant = remember { mutableStateOf("") }
    val plantSearchList = whereToPlantViewModel.plantSearchList.collectAsStateWithLifecycle()
    val currentGarden = whereToPlantViewModel.currentGarden.collectAsStateWithLifecycle()
    Surface() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(140.dp))
            Text("Look Up Your Plant", textAlign = TextAlign.Center)
            TextField(
                value = plant.value,
                onValueChange = {
                    plant.value = it
                    whereToPlantViewModel.getPlantsByName(it)
                },
                Modifier.align(Alignment.CenterHorizontally)
            )
            Surface {
                Column {
                    plantSearchList.value.forEach { plant ->
                        PlantCard(
                            plant,
                            snackbarHostState = snackbarHostState,
                            addGarden = {garden, plantUi ->
                                whereToPlantViewModel.addGardenToPlant(
                                    plantUi = plantUi,
                                    gardenDb = garden.toDb()
                                )
                            },
                            clearGarden = {garden, plantUi ->
                                whereToPlantViewModel.removeGardenFromPlant(
                                    plantUi = plantUi,
                                    gardenName = garden.name
                                )
                            },
                            gardenList = whereToPlantViewModel.gardenList
                        )
                    }
                }
            }
        }
    }
}