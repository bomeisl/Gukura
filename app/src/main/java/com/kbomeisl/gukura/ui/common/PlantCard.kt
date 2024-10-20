package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kbomeisl.gukura.ui.models.PlantUi

@Composable
fun PlantCard(
    plantUi: PlantUi
) {
    Surface(
        modifier = Modifier.padding(15.dp),
        shadowElevation = 5.dp,
        shape = RectangleShape,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    plantUi.name,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(5.dp)
                )
                Row {
                    AsyncImage(
                        model = plantUi.imageUrl,
                        contentDescription = plantUi.description,
                        contentScale = ContentScale.Crop,
                        clipToBounds = true,
                        modifier = Modifier.size(150.dp).padding(5.dp)
                    )
                    Column {
                        Text(plantUi.temperature+" °C", fontFamily = FontFamily.Monospace)
                        Text(plantUi.humidity+" %", fontFamily = FontFamily.Monospace)
                        Text(plantUi.lightLevel+" lux", fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }
    }
}