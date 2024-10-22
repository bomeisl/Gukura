package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kbomeisl.gukura.ui.models.PlantUi

@Composable
fun GardenCard(gardenName: String, plantList: List<PlantUi>) {
    val scrollState = rememberScrollState()
    Surface() {
        Column(Modifier.fillMaxWidth()) {
            Text(gardenName, textAlign = TextAlign.Center)
            Row(modifier = Modifier.horizontalScroll(scrollState)) {
                plantList.forEach {
                    Text(it.name)
                }
            }
        }
    }
}