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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.theme.humidityColor
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HumiditySensorCard(
    humidity: MutableStateFlow<Float>
) {
    val humidityState = humidity.collectAsStateWithLifecycle()
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier.size(300.dp),
        onDraw = {
            drawIntoCanvas { canvas ->

                //humidity gauge
                drawRect(
                    brush = Brush.linearGradient(
                        0.0f to Color.Transparent,
                        0.4f to humidityColor,
                        tileMode = TileMode.Clamp,
                    ),
                    topLeft = Offset(360f, 0f),
                    size = Size(width = humidityState.value.toFloat() * 5, height = 20f),
                    style = Fill
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Outdoor Relative Humidity: " + humidityState.value.toString() + " %",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(270f, 100f)
                )
                canvas.save()
                canvas.restore()

            }
        }
    )
}