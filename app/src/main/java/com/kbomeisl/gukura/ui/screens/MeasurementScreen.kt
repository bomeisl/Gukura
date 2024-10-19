package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.SensorCard
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MeasurementScreen(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Box {
//                    Image(
//                        painter = painterResource(R.drawable.plant_growing),
//                        "",
//                        contentScale = ContentScale.Fit
//                    )
                    SensorCard(
                        temperature = temperature,
                        humidity = humidity,
                        lightLevel = lightLevel
                    )

                }
            }
        }
    }
}