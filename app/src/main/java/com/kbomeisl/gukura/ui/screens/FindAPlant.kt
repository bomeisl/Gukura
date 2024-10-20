package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FindAPlant(
    findAPlantViewModel: FindAPlantViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    var plantSearchText by remember { mutableStateOf("") }
    val plantList = findAPlantViewModel.plantList.collectAsState()
    Surface {
        Row {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(150.dp))
                Text(text = "Find a Plant", color = Color.Black, fontFamily = FontFamily.Monospace)
                TextField(
                    value = plantSearchText,
                    onValueChange = {
                        plantSearchText = it
                        findAPlantViewModel.getPlantsByName(it)
                    }
                )
                plantList.value.forEach {
                    PlantCard(
                        it
                    )
                }
            }
        }
    }
}