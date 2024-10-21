package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kbomeisl.gukura.ui.models.PlantUi

@Composable
fun GardenCard(gardenName: String, plantList: List<PlantUi>) {
    val scrollState = rememberScrollState()
    Column {
        Text(gardenName)
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            plantList.forEach {
                PlantCard(it)
            }
        }
    }
}