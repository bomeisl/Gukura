package com.kbomeisl.gukura.ui.common

import android.hardware.lights.Light
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.data.sensor.LightConditions
import com.kbomeisl.gukura.ui.theme.sunOrange
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SunlightSensorCard(
    lightLevel: MutableStateFlow<Float>
) {
    val lightLevelState = lightLevel.collectAsStateWithLifecycle()
    val textMeasurer = rememberTextMeasurer()
    val lightCondition = remember { mutableStateOf("") }
    if (lightLevelState.value > 10763.9) {
        lightCondition.value = LightConditions.DIRECTLIGHT.condition
    } else {
        if (lightLevelState.value > 5381.96) {
            lightCondition.value = LightConditions.HIGHLIGHT.condition
        } else {
            if (lightLevelState.value > 1076.39) {
                lightCondition.value = LightConditions.MEDIUMBRIGHTLIGHT.condition
            } else {
                if (lightLevelState.value > 269.098) {
                    lightCondition.value = LightConditions.LOWLIGHT.condition
                } else {
                    lightCondition.value = LightConditions.UNSUITABLE.condition
                }
            }
        }
    }
    val canvasSize = 200.dp
    val textXOffest = 20.dp
    val textYOffset = 50.dp
    val textXOffest2 = 30.dp
    val textYOffset2 = 150.dp
    val sunOffsetX = 100.dp
    val sunOffsetY = 100.dp
    Surface(
        modifier = Modifier
            .size(canvasSize),
        shadowElevation = 10.dp,
        shape = CircleShape
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Canvas(
                modifier = Modifier.size(canvasSize),
                onDraw = {
                    drawIntoCanvas { canvas ->
                        //sunlight gauge
                        drawCircle(
                            color = sunOrange,
                            radius = 230f,
                            alpha = lightLevelState.value / 4000,
                            center = Offset(sunOffsetX.toPx(), sunOffsetY.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "Ambient Sunlight: " + lightLevelState.value.toString() + " lux",
                            style = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center
                            ),
                            topLeft = Offset(textXOffest.toPx(), textYOffset.toPx())
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = lightCondition.value,
                            style = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center
                            ),
                            topLeft = Offset(textXOffest2.toPx(), textYOffset2.toPx())
                        )
                        canvas.save()
                        canvas.restore()
                    }
                }
            )
        }
    }
}
