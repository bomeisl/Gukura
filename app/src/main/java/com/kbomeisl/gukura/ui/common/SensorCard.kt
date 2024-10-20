package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.rememberTextMeasurer
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.theme.humidityColor
import com.kbomeisl.gukura.ui.theme.sunOrange
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SensorCard(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>) {
    val temperatureState = temperature.collectAsState()
    val lightLevelState = lightLevel.collectAsState()
    val humidityState = humidity.collectAsState()
    val textMeasurer = rememberTextMeasurer()
    Column(Modifier.fillMaxHeight()) {
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
                            text = "Temperature: " + temperatureState.value.toString() + " °F",
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
        Image(
            painter = painterResource(R.drawable.plant_growing),
            "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}



