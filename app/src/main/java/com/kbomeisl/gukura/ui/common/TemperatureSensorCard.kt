package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun TemperatureSensorCard(
    temperature: MutableStateFlow<Float>
) {
    val temperatureState = temperature.collectAsStateWithLifecycle()
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier.size(300.dp),
        onDraw = {
            drawIntoCanvas { canvas ->

                //temperature gauge
                drawArc(
                    brush = Brush.linearGradient(
                        0.0f to Color.White,
                        0.4f to Color.Blue,
                        tileMode = TileMode.Clamp,
                    ),
                    topLeft = Offset(280f, 0f),
                    startAngle = -180f, // 0 represents 3'0 clock
                    sweepAngle = 2 * temperatureState.value, // size of the arc
                    useCenter = false,
                    style = Stroke(10f, cap = StrokeCap.Round),
                    size = Size(500f, 500f)
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Outdoor Temperature: " + (9 / 5 * temperatureState.value + 12).toString() + " °F",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(400f, 50f)
                )
                canvas.save()
                canvas.restore()
            }
        }
    )
}