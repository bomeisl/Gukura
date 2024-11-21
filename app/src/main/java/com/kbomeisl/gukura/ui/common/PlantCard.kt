package com.kbomeisl.gukura.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.theme.add
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun PlantCard(
    plantUi: PlantUi,
    snackbarHostState: SnackbarHostState,
    addGarden:  (gardenUi: String, plantUi: PlantUi) -> Unit,
    clearGarden: (gardenUi: String, plantUi: PlantUi) -> Unit,
    gardenList: MutableStateFlow<List<GardenUi>> = MutableStateFlow(listOf()),
    showGardenStats: Boolean = false
) {
    val colorToggleHeart = remember { mutableStateOf(false) }
    val heartIconColor = if (colorToggleHeart.value) {
        Color.Red
    } else {
        Color.LightGray
    }
    val colorTogglePlanted = remember { mutableStateOf(false) }
    val plantedIcon = if (colorTogglePlanted.value) {
        Color.Green
    } else {
        Color.LightGray
    }

    val coroutineScope = rememberCoroutineScope()
    val cardExpanded = remember {  mutableStateOf(false) }
    val gardenListUi = gardenList.collectAsStateWithLifecycle()

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
                    modifier = Modifier
                        .size(150.dp)
                        .padding(5.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.thermometer),
                            "",
                            Modifier
                                .size(10.dp)
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
                            Modifier
                                .size(10.dp)
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
                            Modifier
                                .size(10.dp)
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
                        plantUi.gardenName != ""
                                && showGardenStats
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                            Spacer(Modifier.height(20.dp))
                            Text(
                                plantUi.gardenName,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(5.dp)
                            )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.thermometer),
                                            "",
                                            Modifier
                                                .size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = Color.Red
                                        )
                                        Text(
                                            plantUi.gardenTemp + "°F",
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
                                            Modifier
                                                .size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = skyBlue,
                                        )
                                        Text(
                                            plantUi.gardenHumidity + "%",
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
                                            Modifier
                                                .size(10.dp)
                                                .align(Alignment.CenterVertically),
                                            tint = sunOrange
                                        )
                                        Text(
                                            plantUi.gardenLightLevel + " lux",
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

                                    AnimatedVisibility(gardenListUi.value.isNotEmpty()) {
                                        IconButton(
                                            content = {
                                                Column {
                                                    Icon(
                                                        painter = painterResource(R.drawable.trowel),
                                                        "",
                                                        modifier = Modifier.size(40.dp),
                                                        tint = add
                                                    )
                                                }
                                            },
                                            onClick = {
                                                cardExpanded.value = !cardExpanded.value
                                            },
                                        )
                                    }
                                    AnimatedVisibility(plantUi.gardenName.isNotEmpty()) {
                                        IconButton(
                                            content = {
                                                Icon(
                                                    Icons.Outlined.Delete,
                                                    "",
                                                    modifier = Modifier.size(30.dp),
                                                    tint = Color.Black
                                                )
                                            },
                                            onClick = {
                                                
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
                            gardenListUi.value.forEach {
                                Surface(
                                    modifier = Modifier
                                        .clickable {
                                            addGarden(it.name, plantUi)
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "${plantUi.name} added to ${it.name}",
                                                    duration = SnackbarDuration.Short
                                                )
                                            }
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
                                                Modifier
                                                    .size(10.dp)
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
                                                Modifier
                                                    .size(10.dp)
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
                                                Modifier
                                                    .size(10.dp)
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

