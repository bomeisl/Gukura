package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
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
                    topLeft = Offset(250f, 100f),
                    startAngle = -180f, // 0 represents 3'0 clock
                    sweepAngle = 2 * temperatureState.value, // size of the arc
                    useCenter = false,
                    style = Stroke(10f, cap = StrokeCap.Round),
                    size = Size(500f, 500f)
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Temperature: "+temperatureState.value.toString() + "°F",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(280f, 250f)
                )

                //sunlight gauge
                drawCircle(
                    color = sunOrange,
                    radius = 250f,
                    alpha = lightLevelState.value / 40000,
                    center = Offset(510f, 1000f)
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Ambient Sunlight: "+lightLevelState.value.toString() + " lux",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(200f, 900f)
                )

                //humidity gauge
                drawRect(
                    brush = Brush.linearGradient(
                        0.0f to Color.Transparent,
                        0.4f to humidityColor,
                        tileMode = TileMode.Clamp,
                    ),
                    topLeft = Offset(320f,1600f),
                    size = Size(width = humidityState.value*5, height = 20f),
                    style = Fill
                )

                drawText(
                    textMeasurer = textMeasurer,
                    text = "Relative Humidity: "+humidity.value.toString() + " %",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(220f, 1500f)
                )

                canvas.save()
                canvas.restore()
            }
        }
    )
}



