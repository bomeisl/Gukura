package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.ui.common.GardenCard
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.viewmodels.WhereToPlantViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WhereToPlantScreen(
    whereToPlantViewModel: WhereToPlantViewModel = koinViewModel()
) {
    val plant = remember { mutableStateOf("") }
    val gardenList = whereToPlantViewModel.recommendedGardenList.collectAsState()
    Surface() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(140.dp))
            Text("Look Up Your Plant", textAlign = TextAlign.Center)
            TextField(
                value = plant.value,
                onValueChange = {
                    plant.value = it
                    whereToPlantViewModel.getRecommendedGardenList(plant.value)
                },
                Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }
}