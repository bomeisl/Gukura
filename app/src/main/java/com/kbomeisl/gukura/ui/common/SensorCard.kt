package com.kbomeisl.gukura.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.theme.humidityColor
import com.kbomeisl.gukura.ui.theme.sunOrange
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext

@Composable
fun SensorCard(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>,
    geomagneticX: MutableStateFlow<Float>,
    geomagneticY: MutableStateFlow<Float>,
    geomagneticZ: MutableStateFlow<Float>,
    measurementViewModel: MeasurementViewModel,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    gardenName: String = ""
) {
    val temperatureState = measurementViewModel.outsideTemperature.collectAsStateWithLifecycle()
    val lightLevelState = lightLevel.collectAsStateWithLifecycle()
    val humidityState = measurementViewModel.outsideHumidity.collectAsStateWithLifecycle()
    val gardenList = measurementViewModel.gardenList.collectAsStateWithLifecycle()
    val currentGarden = measurementViewModel.currentGarden.collectAsStateWithLifecycle()
    val textMeasurer = rememberTextMeasurer()
    val location by remember { mutableStateOf("") }
    val screenTransitionCue = remember { mutableStateOf(true) }
    val screenTransitionToPlantRecommendations = remember { mutableStateOf(false) }
    val plantList = measurementViewModel.plantList.collectAsStateWithLifecycle()
    val recommendedPlantList = measurementViewModel.recommendedPlantList.collectAsStateWithLifecycle()
    val gardenDropDownExpanded = remember { mutableStateOf(false) }
    val menuText = remember { mutableStateOf("Select a Garden") }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val scrollStateRec = rememberScrollState()
    KoinContext {

        LaunchedEffect(Unit) {
            measurementViewModel.getHumidity()
            measurementViewModel.getTemperature()
        }

        AnimatedVisibility(
            screenTransitionCue.value,
            exit = fadeOut()
        ) {
            Surface(Modifier.defaultMinSize(250.dp)) {
                Column {
                    Box{
                    Canvas(
                        modifier = Modifier.size(1000.dp),
                        onDraw = {
                            drawIntoCanvas { canvas ->

                                //temperature gauge
                                drawArc(
                                    brush = Brush.linearGradient(
                                        0.0f to Color.Blue,
                                        0.4f to Color.Red,
                                        tileMode = TileMode.Clamp,
                                    ),
                                    topLeft = Offset(280f, 300f),
                                    startAngle = -180f, // 0 represents 3'0 clock
                                    sweepAngle = 2 * temperatureState.value, // size of the arc
                                    useCenter = false,
                                    style = Stroke(10f, cap = StrokeCap.Round),
                                    size = Size(500f, 500f)
                                )
                                drawText(
                                    textMeasurer = textMeasurer,
                                    text = "Temperature: " + (9/5*temperatureState.value+12).toString() + " °F",
                                    style = TextStyle(fontFamily = FontFamily.Monospace),
                                    topLeft = Offset(300f, 560f)
                                )

                                //sunlight gauge
                                drawCircle(
                                    color = sunOrange,
                                    radius = 200f,
                                    alpha = lightLevelState.value / 40000,
                                    center = Offset(500f, 970f)
                                )
                                drawText(
                                    textMeasurer = textMeasurer,
                                    text = "Ambient Sunlight: " + lightLevelState.value.toString() + " lux",
                                    style = TextStyle(fontFamily = FontFamily.Monospace),
                                    topLeft = Offset(220f, 930f)
                                )

                                //humidity gauge
                                drawRect(
                                    brush = Brush.linearGradient(
                                        0.0f to Color.Transparent,
                                        0.4f to humidityColor,
                                        tileMode = TileMode.Clamp,
                                    ),
                                    topLeft = Offset(360f, 1250f),
                                    size = Size(width = humidityState.value.toFloat() * 5, height = 20f),
                                    style = Fill
                                )
                                drawText(
                                    textMeasurer = textMeasurer,
                                    text = "Relative Humidity: " + humidityState.value.toString() + " %",
                                    style = TextStyle(fontFamily = FontFamily.Monospace),
                                    topLeft = Offset(270f, 1350f)
                                )

                                canvas.save()
                                canvas.restore()
                            }
                        }
                    )
                    Box(Modifier.align(Alignment.BottomCenter)) {
                        Image(
                            painter = painterResource(R.drawable.plant_growing),
                            "",
                            modifier = Modifier,
                            contentScale = ContentScale.Crop
                        )
                        Row(
                            Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 40.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Surface(
                                    onClick = { gardenDropDownExpanded.value = true },
                                    shape = RoundedCornerShape(50),
                                    shadowElevation = 7.dp,
                                    modifier = Modifier.padding(20.dp)
                                ) {
                                    Row(Modifier.fillMaxWidth()) {
                                        Column(
                                            Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            if (gardenName == "") {
                                                Text(
                                                    text = menuText.value,
                                                    color = Color.Black,
                                                    fontFamily = FontFamily.Monospace,
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                                                )
                                                Icon(Icons.TwoTone.ArrowDropDown, "")
                                            } else {
                                                Text(
                                                    text = gardenName,
                                                    color = Color.Black,
                                                    fontFamily = FontFamily.Monospace,
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                                                )
                                            }

                                        }

                                    }
                                }
                                Button(
                                    content = {
                                        Icon(
                                            painter = painterResource(R.drawable.sensor),
                                            "",
                                            modifier = Modifier.size(30.dp)
                                        )
                                    },
                                    onClick = {
                                        if (currentGarden.value.name.isNotEmpty()) {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Environmental measurements saved for " +
                                                            measurementViewModel.currentGarden.value.name
                                                )
                                            }
                                            screenTransitionToPlantRecommendations.value = true
                                            screenTransitionCue.value = false
                                            measurementViewModel.saveMeasurementToDb(
                                                gardenName = currentGarden.value.name,
                                                temperature = temperatureState.value,
                                                humidity = humidityState.value.toFloat(),
                                                lightLevel = lightLevelState.value
                                            )
                                        } else {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = "Environmental measurements saved for " +
                                                            gardenName
                                                )
                                            }
                                            screenTransitionToPlantRecommendations.value = true
                                            screenTransitionCue.value = false
                                            measurementViewModel.saveMeasurementToDb(
                                                gardenName = gardenName,
                                                temperature = temperatureState.value,
                                                humidity = humidityState.value.toFloat(),
                                                lightLevel = lightLevelState.value
                                            )
                                        }
                                    },
                                    shape = RoundedCornerShape(50),
                                )
                            }
                            DropdownMenu(
                                content = {
                                    gardenList.value.forEach {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                                .horizontalScroll(scrollState)
                                        ) {
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
                                                    measurementViewModel.currentGarden.value =
                                                        it
                                                    measurementViewModel.getPlantsInRange(
                                                        temperature = (9/5*temperatureState.value+12),
                                                        humidity = humidityState.value.toFloat(),
                                                        lightLevel = lightLevelState.value
                                                    )
                                                    menuText.value = it.name
                                                    gardenDropDownExpanded.value = false
                                                },
                                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                                            )
                                        }
                                    }
                                },
                                expanded = gardenDropDownExpanded.value,
                                onDismissRequest = {
                                    gardenDropDownExpanded.value = false
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth(),
                            )
                        }
                    }
                        }
                }
            }
        }
        AnimatedVisibility(
            visible = screenTransitionToPlantRecommendations.value,
            enter = fadeIn()
        ) {
            Surface(Modifier.fillMaxSize()) {
                Row {
                    Column(
                        Modifier.padding(5.dp)
                            .verticalScroll(scrollStateRec)
                    ) {
                        Spacer(Modifier.height(120.dp))
                        if (currentGarden.value.name.isNotEmpty()) {
                            Text(
                                "Your ${currentGarden.value.name} has an average temperature of " +
                                        "${(9 / 5 * temperatureState.value.toInt() + 12)} °F, relative humidity of ${humidityState.value} %," +
                                        " and ambient sunlight levels of ${lightLevelState.value} lux. Here are some " +
                                        "plants that will thrive under these conditions.",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray
                            )
                        } else {
                            Text(
                                "Your ${gardenName} has an average temperature of " +
                                        "${(9 / 5 * temperatureState.value.toInt() + 12)} °F, relative humidity of ${humidityState.value} %," +
                                        " and ambient sunlight levels of ${lightLevelState.value} lux. Here are some " +
                                        "plants that will thrive under these conditions.",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray
                            )
                        }
                        recommendedPlantList.value.forEach { plantItem ->
                            PlantCard(
                                plantItem,
                                snackbarHostState = snackbarHostState,
                                addGarden = { garden, plant ->
                                    measurementViewModel.addGardenToPlant(
                                        plantUi = plant, gardenDb = garden.toDb()
                                    )
                                },
                                clearGarden = { garden, plant ->
                                    measurementViewModel.removeGardenFromPlant(
                                        plantUi = plant, gardenName = garden.name
                                    )
                                },
                                gardenList = measurementViewModel.gardenList
                            )
                        }
                    }
                }
            }
        }
    }
    }



