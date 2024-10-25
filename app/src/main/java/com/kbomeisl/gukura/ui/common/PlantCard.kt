package com.kbomeisl.gukura.ui.common

import android.text.Layout
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.theme.add
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify

@Composable
fun PlantCard(
    plantUi: PlantUi,
    gardenList: List<GardenUi>,
    snackbarHostState: SnackbarHostState,
    addGarden: (plant: PlantUi, garden: String) -> Unit,
    removeGarden: (plant: PlantUi, garden: String) -> Unit,
) {
    val colorToggleHeart = remember { mutableStateOf(false) }
    val colorTogglePlanted = remember { mutableStateOf(false) }
    val heartIconColor = if (colorToggleHeart.value) {
        Color.Red
    } else {
        Color.LightGray
    }
    val plantedIcon = if (colorTogglePlanted.value) {
        Color.Green
    } else {
        Color.LightGray
    }

    val coroutineScope = rememberCoroutineScope()
    val gardenName = remember { mutableStateOf(plantUi.garden) }
    val garden = gardens.gardenList.filter { it.name == gardenName.value }
    val currentGardenUi: MutableState<GardenUi?> = remember { mutableStateOf(garden.firstOrNull()) }
    val cardExpanded = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shadowElevation = 5.dp,
        shape = RectangleShape,
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                plantUi.name,
                color = nightBlue,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(5.dp)
            )
            Row {
                AsyncImage(
                    model = plantUi.imageUrl,
                    contentDescription = plantUi.description,
                    contentScale = ContentScale.Fit,
                    clipToBounds = true,
                    modifier = Modifier.size(150.dp).padding(5.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.thermometer),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Red
                        )
                        Text(
                            plantUi.temperature + " °C",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.humidity),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = skyBlue,
                        )
                        Text(
                            plantUi.humidity + " %",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.sun_2),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = sunOrange
                        )
                        Text(
                            plantUi.lightLevel + " lux",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                        AnimatedVisibility(
                        plantUi.garden != ""
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(Modifier.height(20.dp))
                            Text(
                                plantUi.garden,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(5.dp)
                            )
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.thermometer),
                                            "",
                                            Modifier.size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = Color.Red
                                        )
                                        Text(
                                            currentGardenUi.value?.avgTemperature + "°F",
                                            fontFamily = FontFamily.Monospace
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.humidity),
                                            "",
                                            Modifier.size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = skyBlue,
                                        )
                                        Text(
                                            currentGardenUi.value?.avgHumidity + "%",
                                            fontFamily = FontFamily.Monospace
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.sun_2),
                                            "",
                                            Modifier.size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = sunOrange
                                        )
                                        Text(
                                            currentGardenUi.value?.avgLightLevel + " lux",
                                            fontFamily = FontFamily.Monospace
                                        )
                                    }
                                }
                        }

                    }
                        Spacer(Modifier.height(10.dp))
                        Surface {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    IconButton(
                                        content = {
                                            Icon(
                                                painter = painterResource(R.drawable.heart),
                                                "",
                                                modifier = Modifier.size(30.dp),
                                                tint = heartIconColor
                                            )
                                        },
                                        onClick = {

                                            if (!colorToggleHeart.value) {
                                                coroutineScope.launch {
                                                    snackbarHostState.showSnackbar(
                                                        message = "${plantUi.name} wishlisted",
                                                        duration = SnackbarDuration.Short
                                                    )
                                                }
                                            }

                                            if (colorToggleHeart.value) {
                                                coroutineScope.launch {
                                                    snackbarHostState.showSnackbar(
                                                        message = "${plantUi.name} removed from wishlist",
                                                        duration = SnackbarDuration.Short
                                                    )
                                                }
                                            }
                                            colorToggleHeart.value = !colorToggleHeart.value
                                        }
                                    )

                                    AnimatedVisibility(currentGardenUi.value == null) {
                                        IconButton(
                                            content = {
                                                Column {
                                                    Icon(
                                                        painter = painterResource(R.drawable.trowel),
                                                        "",
                                                        modifier = Modifier.size(40.dp),
                                                        tint = plantedIcon
                                                    )
                                                    Text("Plant", modifier = Modifier.padding(5.dp))
                                                }
                                            },
                                            onClick = {
                                                colorTogglePlanted.value = !colorTogglePlanted.value
                                                cardExpanded.value = !cardExpanded.value
                                            },
                                        )
                                    }
                                    AnimatedVisibility(currentGardenUi.value == null) {
                                        IconButton(
                                            content = {
                                                Icon(
                                                    Icons.Outlined.Delete,
                                                    "",
                                                    modifier = Modifier.size(30.dp),
                                                    tint = Color.Red
                                                )
                                            },
                                            onClick = {
                                                if (currentGardenUi.value != null) {
                                                    removeGarden(
                                                        plantUi,
                                                        currentGardenUi.value!!.name
                                                    )
                                                }
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(cardExpanded.value) {
                    Surface(Modifier.fillMaxWidth()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            gardens.gardenList.forEach {
                                Surface(
                                    modifier = Modifier
                                        .clickable {
                                            currentGardenUi.value = it
                                            cardExpanded.value = false
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "${plantUi.name} added to ${it.name}",
                                                    duration = SnackbarDuration.Short
                                                )
                                            }
                                            addGarden(plantUi, it.name)
                                        }
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(it.name)
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.thermometer),
                                                "",
                                                Modifier.size(10.dp)
                                                    .align(Alignment.CenterVertically),
                                                tint = Color.Red
                                            )
                                            Text(
                                                it.avgTemperature + "°F",
                                                fontFamily = FontFamily.Monospace
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.humidity),
                                                "",
                                                Modifier.size(10.dp)
                                                    .align(Alignment.CenterVertically),
                                                tint = skyBlue,
                                            )
                                            Text(
                                                it.avgHumidity + "%",
                                                fontFamily = FontFamily.Monospace
                                            )
                                        }
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.sun_2),
                                                "",
                                                Modifier.size(10.dp)
                                                    .align(Alignment.CenterVertically),
                                                tint = sunOrange
                                            )
                                            Text(
                                                it.avgLightLevel + " lux",
                                                fontFamily = FontFamily.Monospace
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
    }

