package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.GardenCard
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.viewmodels.GrowthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantGrowthScreen(
    growthViewModel: GrowthViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    LaunchedEffect(Unit) {
        growthViewModel.populateGardenList()
    }
    val gardenList = growthViewModel.gardenList.collectAsStateWithLifecycle()
    Surface() {
        Column {
            Spacer(Modifier.height(130.dp))
            Row {
                gardenList.value.forEach {
                    GardenCard(
                        gardenUi = it,
                        onClick = {},
                        removeGarden = {},
                        navController = navHostController
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.plant),
                    "",
                    tint = forestGreen
                )
                Image(
                    painter = painterResource(R.drawable.scale),
                    ""
                )
            }
        }
    }
}
