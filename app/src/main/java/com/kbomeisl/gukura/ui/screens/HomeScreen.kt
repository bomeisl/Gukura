package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.ui.common.GardenCard
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.theme.add
import com.kbomeisl.gukura.ui.theme.bgCard
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val logTag = "Home Screen"
    LaunchedEffect(Unit) {
        homeViewModel.getGardens()
    }

    val scrollstate = rememberScrollState()
    val gardenPlantList = homeViewModel.gardenPlantList.collectAsState()
    val expandCard = remember { mutableStateOf(false) }
    val newGardenName = remember { mutableStateOf("") }
    val gardenList = homeViewModel.gardenList.collectAsState()

    Surface {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            stickyHeader {
                    Spacer(Modifier.height(100.dp))
                    Text(
                        "Gardens",
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = bgCard
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(Modifier.horizontalScroll(scrollstate)) {
                                gardenList.value.forEach {
                                    GardenCard(
                                        it,
                                        onClick = {
                                            homeViewModel.getPlantsInGarden(it.name)
                                        },
                                        removeGarden = { homeViewModel.deleteGarden(it) },
                                        navController = navHostController
                                    )
                                }
                            }
                            FloatingActionButton(
                                modifier = Modifier.padding(5.dp),
                                content = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            Icons.Outlined.Add,
                                            "",
                                            tint = add,
                                            modifier = Modifier.clickable {
                                                expandCard.value = !expandCard.value
                                            }
                                        )

                                        AnimatedVisibility(expandCard.value) {
                                            Row {
                                                TextField(
                                                    value = newGardenName.value,
                                                    onValueChange = {
                                                        newGardenName.value = it
                                                    },
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .align(Alignment.CenterVertically)
                                                    ,
                                                    label = {
                                                        Text(
                                                            "Add a New Garden",
                                                            textAlign = TextAlign.Center,
                                                            modifier = Modifier.padding(5.dp)
                                                        )
                                                            },
                                                    placeholder = { Text("Ex: 'Bedroom Dresser") },
                                                    colors = TextFieldDefaults.colors(
                                                        disabledContainerColor = Color.White,
                                                        unfocusedContainerColor = Color.White,
                                                        unfocusedLabelColor = Color.Gray,
                                                        focusedContainerColor = Color.White,
                                                        unfocusedTextColor = Color.Gray
                                                    ),
                                                    trailingIcon = {
                                                        Icon(
                                                            Icons.Outlined.Send,
                                                            "",
                                                            tint = Color.Gray,
                                                            modifier = Modifier.clickable {
                                                                homeViewModel.addGarden(
                                                                    GardenDb(
                                                                        gardenId = newGardenName.value.hashCode(),
                                                                        name = newGardenName.value
                                                                    )
                                                                )
                                                                expandCard.value = false
                                                            }
                                                        )
                                                    }
                                                )

                                            }
                                        }
                                    }
                                },
                                onClick = {},
                                containerColor = Color.White
                            )
                        }
                    }
            }

            items(gardenPlantList.value) {
                    PlantCard(
                        it,
                        snackbarHostState = snackbarHostState,
                        addGarden = { garden, plantUi ->
                            homeViewModel.addGardenToPlant(
                                plantUi = plantUi,
                                gardenDb = garden.toDb()
                            )
                        },
                        clearGarden = { garden, plantUi ->
                            homeViewModel.removeGardenFromPlant(
                                plantUi = plantUi,
                                gardenName = garden.name
                            )
                        },
                        gardenList = homeViewModel.gardenList
                    )
                }
        }

    }
}






