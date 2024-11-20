package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.testData.plants
import com.kbomeisl.gukura.ui.viewmodels.WishListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyWishListScreen(
    wishListViewModel: WishListViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        wishListViewModel.getWishlistedPlants()
    }
    val wishlistedPlants = wishListViewModel.wishlistedPlants.collectAsStateWithLifecycle()
    Surface {
        LazyColumn {
            items(wishlistedPlants.value) { plant ->
                PlantCard(
                    plantUi = plant,
                    snackbarHostState = snackbarHostState,
                    addGarden = { garden, plantUi ->  wishListViewModel.addGardenToPlant(plantUi = plantUi, gardenDb = garden.toDb())},
                    clearGarden = { garden, plantUi ->  wishListViewModel.removeGardenFromPlant(plantUi = plantUi, gardenName = garden.name)},
                    gardenList = wishListViewModel.gardenList,
                    showGardenStats = false
                )
            }
        }
    }
}