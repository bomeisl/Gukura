package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import android.health.connect.datatypes.units.Temperature
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
import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.mutualmobile.composesensors.rememberAmbientTemperatureSensorState
import com.mutualmobile.composesensors.rememberLightSensorState
import com.mutualmobile.composesensors.rememberRelativeHumiditySensorState
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MeasurementScreen() {
    val temperatureState = rememberAmbientTemperatureSensorState()
    val lightState = rememberLightSensorState()
    val humiditySensorState = rememberRelativeHumiditySensorState()
    Surface {
        Row {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    temperatureState.temperature.toString(),
                    color = Color.Black
                )
                Text(
                    lightState.illuminance.toString(),
                    color = Color.Red
                )
                Text(
                    humiditySensorState.relativeHumidity.toString(),
                    color = Color.Blue
                )
            }
        }
    }
}