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
import androidx.compose.material.icons.twotone.Add
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
import com.kbomeisl.gukura.ui.theme.add
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange
import kotlinx.coroutines.launch

@Composable
fun PlantCard(
    plantUi: PlantUi,
    gardenList: List<GardenUi>,
    snackbarHostState: SnackbarHostState
) {
    val colorToggleHeart = remember { mutableStateOf(false) }
    val colorTogglePlanted = remember { mutableStateOf(false) }
    val heartIconColor = if (colorToggleHeart.value) {Color.Red} else {Color.LightGray}
    val plantedIcon = if (colorTogglePlanted.value) {Color.Green} else {Color.Gray}
    val coroutineScope = rememberCoroutineScope()
    val garden = remember { mutableStateOf(GardenUi()) }
    val cardExpanded = remember { mutableStateOf(false) }
    val deleteIcon = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.padding(15.dp),
        shadowElevation = 5.dp,
        shape = RectangleShape,
    ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    plantUi.name,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(5.dp)
                )
                Row {
                    AsyncImage(
                        model = plantUi.imageUrl,
                        contentDescription = plantUi.description,
                        contentScale = ContentScale.Crop,
                        clipToBounds = true,
                        modifier = Modifier.size(150.dp).padding(5.dp)
                    )
                    Column {
                        Text(plantUi.temperature + " °C", fontFamily = FontFamily.Monospace)
                        Text(plantUi.humidity + " %", fontFamily = FontFamily.Monospace)
                        Text(plantUi.lightLevel + " lux", fontFamily = FontFamily.Monospace)
                        Text(garden.value.name, color = Color.Gray)
                        Spacer(Modifier.height(10.dp))
                        Surface {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
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
                                    Column(Modifier.fillMaxWidth()) {
                                        IconButton(
                                            content = {
                                                Column {
                                                    Icon(
                                                        painter = painterResource(R.drawable.trowel),
                                                        "",
                                                        modifier = Modifier.size(30.dp),
                                                        tint = plantedIcon
                                                    )
                                                    Text("Plant", modifier = Modifier.padding(5.dp))
                                                }
                                            },
                                            onClick = {
                                                if (!colorTogglePlanted.value) {
                                                    coroutineScope.launch {
                                                        snackbarHostState.showSnackbar(
                                                            message = "${plantUi.name} wishlisted",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                    }
                                                }

                                                if (colorTogglePlanted.value) {
                                                    coroutineScope.launch {
                                                        snackbarHostState.showSnackbar(
                                                            message = "${plantUi.name} removed from wishlist",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                    }
                                                }
                                                colorTogglePlanted.value = !colorTogglePlanted.value
                                                cardExpanded.value = !cardExpanded.value
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
                            gardenList.forEach {
                                Surface(
                                    modifier = Modifier
                                        .clickable {
                                            garden.value = it
                                            cardExpanded.value = false
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
