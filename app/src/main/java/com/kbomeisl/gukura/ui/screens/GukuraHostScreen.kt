package com.kbomeisl.gukura.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.viewmodel.factory.KoinViewModelFactory

@Composable
fun GukuraNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
           NavHost(
               navController = navController,
               startDestination = Routes.HOME.name
           ) {
               composable(route = Routes.HOME.name) {
                   HomeScreen(
                       navHostController = navController,
                       snackbarHostState = snackbarHostState
                   )
               }
               composable(route = Routes.FINDAPLANT.name) {
//
               }
               composable(route = Routes.WHERETOPLANT.name) {
                   WhereToPlantScreen(snackbarHostState=snackbarHostState)
               }
               composable("${Routes.MEASURE.name}/{gardenName}") { navBackStackEntry ->
                   val gardenName = navBackStackEntry.arguments?.getString("gardenName")
                   if (!gardenName.isNullOrEmpty()) {
                       MeasurementScreen(
                           navHostController = navController,
                           snackbarHostState = snackbarHostState,
                           gardenName = gardenName
                       )
                   } else {
                       MeasurementScreen(
                           navHostController = navController,
                           snackbarHostState = snackbarHostState
                       )
                   }

               }
               composable(route = Routes.MYPLANTS.name) {
                   MyPlantsScreen()
               }
               composable(route = Routes.PLANTDB.name) {
                   PlantDatabaseScreen(snackbarHostState = snackbarHostState)
               }
               composable(route = "${Routes.PLANTRECOMMENDATIONS.name}/{heading}/{light}/{gardenName}") { navBackStackEntry ->
                   val heading = navBackStackEntry.arguments?.getString("heading")
                   val light = navBackStackEntry.arguments?.getString("light")
                   val gardenName = navBackStackEntry.arguments?.getString("gardenName")
                   light?.also {
                       heading?.also {
                           gardenName?.also {
                               PlantRecommendationScreen(
                                   heading = heading,
                                   light = light,
                                   gardenName = gardenName,
                                   snackbarHostState = snackbarHostState
                               )
                           }
                       }
                   }
               }
               composable(route = Routes.GROWTH.name) {
                   PlantGrowthScreen(
                       navHostController = navController
                   )
               }
               composable(route = Routes.WISHLIST.name) {
                   MyWishListScreen(
                       snackbarHostState = snackbarHostState
                   )
               }
               composable(route = "${Routes.GARDENGROWTH.name}/{gardenName") { navBackStackEntry: NavBackStackEntry ->
                   val gardenName = navBackStackEntry.arguments?.getString("gardenName")
                   gardenName?.also {
                       GardenGrowthScreen(
                           gardenName = it,
                           navHostController = navController
                       )
                   }
               }
           }
       }