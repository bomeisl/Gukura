package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MeasurementScreen() {
//    val temperatureState = rememberAmbientTemperatureSensorState()
//    val lightState = rememberLightSensorState()
//    val humiditySensorState = rememberRelativeHumiditySensorState()
//    val magnetomometerState = rememberMagneticFieldSensorState()
    Surface {
        Row {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    "Standby...",
                    //temperatureState.temperature.toString(),
                    color = Color.Black
                )
                Text(
                    "Standby...",
                    //lightState.illuminance.toString(),
                    color = Color.Red
                )
                Text(
                    "Standby...",
                    //humiditySensorState.relativeHumidity.toString(),
                    color = Color.Blue
                )
            }
        }
    }
}