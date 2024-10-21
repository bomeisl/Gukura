package com.kbomeisl.gukura.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.theme.humidityColor
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.sunOrange
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.compose.KoinContext
import org.koin.core.context.KoinContext

@Composable
fun SensorCard(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>,
    measurementViewModel: MeasurementViewModel,
    navHostController: NavHostController
) {

    val temperatureState = temperature.collectAsState()
    val lightLevelState = lightLevel.collectAsState()
    val humidityState = humidity.collectAsState()
    val textMeasurer = rememberTextMeasurer()
    var location by remember { mutableStateOf("") }
    var screenTransitionCue by remember { mutableStateOf(true) }
    var screenTransitionToPlantRecommendations by remember { mutableStateOf(false) }
    val plantList = measurementViewModel.plantList.collectAsState()
    KoinContext {

        AnimatedVisibility(
            screenTransitionCue,
            exit = fadeOut()
        ) {
            Column(Modifier.fillMaxHeight().padding(top = 50.dp)) {
                Canvas(
                    modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(.6f),
                    onDraw = {
                        drawIntoCanvas { canvas ->

                            //temperature gauge
                            drawArc(
                                brush = Brush.linearGradient(
                                    0.0f to Color.Blue,
                                    0.4f to Color.Red,
                                    tileMode = TileMode.Clamp,
                                ),
                                topLeft = Offset(380f, 200f),
                                startAngle = -180f, // 0 represents 3'0 clock
                                sweepAngle = 2 * temperatureState.value, // size of the arc
                                useCenter = false,
                                style = Stroke(10f, cap = StrokeCap.Round),
                                size = Size(600f, 600f)
                            )
                            drawText(
                                textMeasurer = textMeasurer,
                                text = "Temperature: " + temperatureState.value.toString() + " °C",
                                style = TextStyle(fontFamily = FontFamily.Monospace),
                                topLeft = Offset(400f, 460f)
                            )

                            //sunlight gauge
                            drawCircle(
                                color = sunOrange,
                                radius = 300f,
                                alpha = lightLevelState.value / 40000,
                                center = Offset(670f, 1170f)
                            )
                            drawText(
                                textMeasurer = textMeasurer,
                                text = "Ambient Sunlight: " + lightLevelState.value.toString() + " lux",
                                style = TextStyle(fontFamily = FontFamily.Monospace),
                                topLeft = Offset(300f, 1130f)
                            )

                            //humidity gauge
                            drawRect(
                                brush = Brush.linearGradient(
                                    0.0f to Color.Transparent,
                                    0.4f to humidityColor,
                                    tileMode = TileMode.Clamp,
                                ),
                                topLeft = Offset(450f, 1650f),
                                size = Size(width = humidityState.value * 5, height = 20f),
                                style = Fill
                            )
                            drawText(
                                textMeasurer = textMeasurer,
                                text = "Relative Humidity: " + humidity.value.toString() + " %",
                                style = TextStyle(fontFamily = FontFamily.Monospace),
                                topLeft = Offset(350f, 1550f)
                            )

                            canvas.save()
                            canvas.restore()
                        }
                    }
                )
                Box {
                    Image(
                        painter = painterResource(R.drawable.plant_growing),
                        "",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 40.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = location,
                            onValueChange = {
                                location = it
                            },
                            placeholder = { Text("Ex: 'Kitchen'", textAlign = TextAlign.Center) },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.location_sign),
                                    "",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(),
                            trailingIcon = {
                                Button(
                                    content = {
                                        Row(Modifier.padding(5.dp)) {
                                            Icon(
                                                painter = painterResource(R.drawable.sensor),
                                                "",
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }
                                    },
                                    onClick = {
                                        measurementViewModel.populatePlantList(
                                            temperature = temperatureState.value,
                                            humidity = humidityState.value,
                                            lightLevel = lightLevelState.value,
                                            location = location
                                        )
                                        measurementViewModel.saveMeasurementToDb(
                                            temperature = temperatureState.value,
                                            humidity = humidityState.value,
                                            lightLevel = lightLevelState.value,
                                            location = location
                                        )
                                        screenTransitionCue = false
                                        screenTransitionToPlantRecommendations = true
                                    },
                                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp),
                                    shape = RectangleShape
                                )
                            }
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            screenTransitionToPlantRecommendations,
            enter = fadeIn()
        ) {
            Surface(Modifier.fillMaxSize()) {
                Row {
                    Column(Modifier.padding(5.dp)) {
                        Spacer(Modifier.height(110.dp))
                        Text(
                            "Your ${location} has an average temperature of " +
                                    "${temperatureState.value} °C, relative humidity of ${humidityState.value} %," +
                                    " and ambient sunlight level of ${lightLevelState.value} lux. Here are some " +
                                    "plants that will thrive under these conditions.",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                        plantList.value.forEach {
                            PlantCard(it)
                        }
                    }
                }
            }
        }
    }
}


