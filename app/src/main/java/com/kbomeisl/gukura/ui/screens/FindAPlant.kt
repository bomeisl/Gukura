package com.kbomeisl.gukura.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FindAPlant(
    navHostController: NavHostController,
    findAPlantViewModel: FindAPlantViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
) {
    var plantSearchText by remember { mutableStateOf("") }
    val plantList = findAPlantViewModel.plantList.collectAsState()
    val recommendedPlants = findAPlantViewModel.recommendedPlants.collectAsState()
    val scrollState = rememberScrollState()
    var floweringDropDownExpanded by remember { mutableStateOf(false) }
    var annualDropDownExpanded by remember { mutableStateOf(false) }
    var sizeDropDownExpanded by remember { mutableStateOf(false) }
    var gardenDropDownExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { findAPlantViewModel.populatePlantList() }

    Surface(Modifier.fillMaxSize()) {
        Row {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(130.dp))

                AnimatedVisibility( sizeDropDownExpanded ) {
                    Spacer(Modifier.height(130.dp))
                }
                Spacer(Modifier.height(15.dp))

                Surface(onClick = { gardenDropDownExpanded = true }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Select a Garden",
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                        Icon(Icons.TwoTone.ArrowDropDown, "")
                        AnimatedVisibility( findAPlantViewModel.garden.value.name != "" ) {
                            Row {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(findAPlantViewModel.garden.value.name, color = Color.Gray)
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                                            Icon(
                                                painter = painterResource(R.drawable.thermometer),
                                                "",
                                                Modifier.size(10.dp).align(Alignment.CenterVertically),
                                                tint = Color.Red
                                            )
                                            Text(findAPlantViewModel.garden.value.avgTemperature + "°F", fontFamily = FontFamily.Monospace)
                                        }
                                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                                            Icon(
                                                painter = painterResource(R.drawable.humidity),
                                                "",
                                                Modifier.size(10.dp).align(Alignment.CenterVertically),
                                                tint = skyBlue,
                                            )
                                            Text(findAPlantViewModel.garden.value.avgHumidity + "%", fontFamily = FontFamily.Monospace)
                                        }
                                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                                            Icon(
                                                painter = painterResource(R.drawable.sun_2),
                                                "",
                                                Modifier.size(10.dp).align(Alignment.CenterVertically),
                                                tint = sunOrange
                                            )
                                            Text(findAPlantViewModel.garden.value.avgLightLevel + " lux", fontFamily = FontFamily.Monospace)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    DropdownMenu(
                        content = {
                            gardens.gardenList.forEach {
                                Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) {
                                    DropdownMenuItem(
                                        text = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    it.name,
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontFamily = FontFamily.Monospace,
                                                    textAlign = TextAlign.Center,
                                                )
                                            }
                                        },
                                        onClick = {
                                            findAPlantViewModel.garden.value =
                                                it
                                            findAPlantViewModel.getPlantsInRange(
                                                it.avgTemperature.toFloat(),
                                                it.avgHumidity.toFloat(),
                                                it.avgLightLevel.toFloat()
                                            )
                                            gardenDropDownExpanded = false
                                        },
                                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        },
                        expanded = gardenDropDownExpanded,
                        onDismissRequest = {
                            gardenDropDownExpanded = false
                                           },
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }

                AnimatedVisibility( sizeDropDownExpanded ) {
                    Spacer(Modifier.height(130.dp))
                }
                Spacer(Modifier.height(5.dp))
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))

                AnimatedVisibility(
                    (
                        findAPlantViewModel.garden.value.name != ""
                    )
                ) {
                    Surface() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Text(
                                    "Recommended Plants",
                                    color = nightBlue,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 20.sp
                                )
                            }
                            Column(Modifier.verticalScroll(scrollState)) {
                                recommendedPlants.value.forEach {
                                    PlantCard(
                                        it,
                                        findAPlantViewModel.garden.value,
                                        snackbarHostState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}