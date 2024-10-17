package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.ui.models.PlantUi

@Composable
fun PlantCard(
    plantUi: PlantUi
) {
    Surface(
        shadowElevation = 10.dp,
        shape = RectangleShape
    ) {
        Row {
            Text(plantUi.name)
            //Image(painter = painterResource(plantUi.pictureId), plantUi.name)
        }
    }
}