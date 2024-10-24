package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange

@Composable
fun GardenCard(
    gardenUi: GardenUi,
    onClick: (GardenUi) -> Unit
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick(gardenUi)
            },
        shadowElevation = 1.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(gardenUi.name, textAlign = TextAlign.Center, fontFamily = FontFamily.Monospace)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.thermometer),
                        "",
                        Modifier.size(10.dp).align(Alignment.CenterVertically),
                        tint = Color.Red
                    )
                    Text(gardenUi.avgTemperature + "°F", fontFamily = FontFamily.Monospace)
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.humidity),
                        "",
                        Modifier.size(10.dp).align(Alignment.CenterVertically),
                        tint = skyBlue,
                    )
                    Text(gardenUi.avgHumidity + "%", fontFamily = FontFamily.Monospace)
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.sun_2),
                        "",
                        Modifier.size(10.dp).align(Alignment.CenterVertically),
                        tint = sunOrange
                    )
                    Text(gardenUi.avgLightLevel + " lux", fontFamily = FontFamily.Monospace)
                }
            }
        }
    }
}