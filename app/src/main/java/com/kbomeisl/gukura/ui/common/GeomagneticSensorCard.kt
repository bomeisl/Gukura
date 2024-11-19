package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.cos
import kotlin.math.roundToLong
import kotlin.math.sin

@Composable
fun GeomagneticSensorCard(
    magneticOrientation: MutableStateFlow<Float>
) {
    val magneticOrientationState = magneticOrientation.collectAsStateWithLifecycle()
    val textMeasurer = rememberTextMeasurer()
    val R = 150.dp
    val canvasSize = 150.dp
    val compassYOffset = 0.dp
    val compassXOffset = 0.dp
    val needleStart = 75.dp
    val northXOffset = 67.dp
    val northYOffset = 5.dp
    val southXOffset = 67.dp
    val southYOffset = 130.dp
    val eastXOffset = 137.dp
    val eastYOffset = 65.dp
    val westXOffset = 5.dp
    val westYOffset = 65.dp
    val textOffset = 20.dp
    Surface(shadowElevation = 10.dp, shape = CircleShape) {
        Row(horizontalArrangement = Arrangement.Center) {
            Canvas(
                modifier = Modifier.size(canvasSize),
                onDraw = {
                    drawIntoCanvas { canvas ->
                        //compass readings
                        drawArc(
                            brush = Brush.linearGradient(
                                0.0f to Color.LightGray,
                                0.4f to Color.LightGray,
                                tileMode = TileMode.Clamp,
                            ),
                            topLeft = Offset(0f, 0f),
                            startAngle = -180f, // 0 represents 3'0 clock
                            sweepAngle = 360f, // size of the arc
                            useCenter = true,
                            style = Stroke(10f, cap = StrokeCap.Round),
                            size = Size(R.toPx(), R.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "N",
                            style = TextStyle(fontFamily = FontFamily.Monospace),
                            topLeft = Offset(northXOffset.toPx(), northYOffset.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "S",
                            style = TextStyle(fontFamily = FontFamily.Monospace),
                            topLeft = Offset(southXOffset.toPx(), southYOffset.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "E",
                            style = TextStyle(fontFamily = FontFamily.Monospace),
                            topLeft = Offset(eastXOffset.toPx(), eastYOffset.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "W",
                            style = TextStyle(fontFamily = FontFamily.Monospace),
                            topLeft = Offset(westXOffset.toPx(), westYOffset.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = magneticOrientationState.value.toString(),
                            style = TextStyle(fontFamily = FontFamily.Monospace),
                            topLeft = Offset(southXOffset.toPx(), southYOffset.toPx()-textOffset.toPx())
                        )
                        drawLine(
                            brush = Brush.linearGradient(
                                0.0f to Color.Red,
                                0.4f to Color.Red,
                                tileMode = TileMode.Clamp,
                            ),
                            start = Offset(needleStart.toPx(), needleStart.toPx()),
                            end = Offset(
                                (cos(Math.toRadians(magneticOrientationState.value.toDouble() - 90)) * (R / 2).toPx() + needleStart.toPx()).toFloat(),
                                (sin(Math.toRadians(magneticOrientationState.value.toDouble() - 90)) * (R / 2).toPx() + needleStart.toPx()).toFloat()
                            )
                        )
                    }
                }
            )
        }
    }
}