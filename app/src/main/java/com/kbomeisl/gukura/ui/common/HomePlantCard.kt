package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.theme.sunOrange

@Composable
fun HomePlantCard(
    plantUi: PlantUi
) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shadowElevation = 5.dp,
        shape = RectangleShape,
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                plantUi.name,
                color = nightBlue,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(5.dp)
            )
            Row {
                AsyncImage(
                    model = plantUi.imageUrl,
                    contentDescription = plantUi.description,
                    contentScale = ContentScale.Crop,
                    clipToBounds = false,
                    modifier = Modifier.size(150.dp).padding(5.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.thermometer),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Red
                        )
                        Text(
                            plantUi.temperature + " °C",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.humidity),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = skyBlue,
                        )
                        Text(
                            plantUi.humidity + " %",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.sun_2),
                            "",
                            Modifier.size(10.dp)
                                .align(Alignment.CenterVertically),
                            tint = sunOrange
                        )
                        Text(
                            plantUi.lightLevel + " lux",
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}