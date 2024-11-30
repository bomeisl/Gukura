package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.data.database.models.GardenDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.ui.common.GardenCard
import com.kbomeisl.gukura.ui.viewmodels.GardenGrowthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GardenGrowthScreen(
    gardenGrowthViewModel: GardenGrowthViewModel = koinViewModel<GardenGrowthViewModel>(),
    navHostController: NavHostController,
    gardenName: String
) {
    lateinit var garden: GardenDb
    LaunchedEffect(Unit) {
        garden = gardenGrowthViewModel.lookUpGardenByName(gardenName)
    }
    Surface {
        Column {
            Spacer(Modifier.height(130.dp))
            GardenCard(
                gardenUi = garden.toUi(),
                onClick = {},
                removeGarden = {},
                navController = navHostController
            )
        }
    }
}