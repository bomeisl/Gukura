package com.kbomeisl.gukura.ui.common

import android.hardware.lights.Light
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
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

    Canvas(
        modifier = Modifier.size(300.dp),
        onDraw = {
            drawIntoCanvas { canvas ->
                //sunlight gauge
                drawCircle(
                    color = sunOrange,
                    radius = 200f,
                    alpha = lightLevelState.value / 4000,
                    center = Offset(500f, 500f)
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = "Ambient Sunlight: " + lightLevelState.value.toString() + " lux",
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(230f, 400f)
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = lightCondition.value,
                    style = TextStyle(fontFamily = FontFamily.Monospace),
                    topLeft = Offset(300f, 550f)
                )
                canvas.save()
                canvas.restore()
            }
        }
    )
}
