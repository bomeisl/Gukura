package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantDatabaseScreen(
    findAPlantViewModel: FindAPlantViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    var plantName = remember { mutableStateOf("") }
    val plantList = findAPlantViewModel.plantList.collectAsStateWithLifecycle()
    val gardenList = findAPlantViewModel.gardenList.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(plantName.value) {
        findAPlantViewModel.getPlantsByName(plantName.value)
    }

    LaunchedEffect(Unit) {
        findAPlantViewModel.populateGardenList()
    }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item { Spacer(Modifier.height(120.dp)) }
                stickyHeader {
                    Text("Look Up a Plant", fontFamily = FontFamily.Monospace)
                    TextField(
                        value = plantName.value,
                        onValueChange = { plantName.value = it}
                    )
                }
                    items(plantList.value) {
                    PlantCard(
                        it,
                        snackbarHostState = snackbarHostState,
                        addGarden = { gardenUi, plantUi ->  
                            
                        },
                        clearGarden = { gardenUi, plantUi ->  
                        },
                        gardenList = findAPlantViewModel.gardenList
                    )
                }
            }
        }


