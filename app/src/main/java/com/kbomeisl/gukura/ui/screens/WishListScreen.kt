package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.PlantUi
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
    val currentGarden =
    Surface {
        LazyColumn {
            items(wishlistedPlants.value) { plant ->
                PlantCard(
                    plantUi = plant,
                    snackbarHostState = snackbarHostState,
                    addGarden = {gardenName: String, plantUi: PlantUi ->  },
                    clearGarden = {gardenName: String, plantUi: PlantUi ->  },
                    gardenList = wishListViewModel.gardenList,
                    showGardenStats = false
                )
            }
        }
    }
}