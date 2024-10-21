package com.kbomeisl.gukura.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun GukuraNavHost(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>,
    navController: NavHostController
) {
           NavHost(
               navController = navController,
               startDestination = Routes.HOME.name
           ) {
               composable(route = Routes.HOME.name) {
                   HomeScreen(
                       navHostController = navController
                   )
               }
               composable(route = Routes.FINDAPLANT.name) {
                   FindAPlant(navHostController = navController)
               }
               composable(route = Routes.WHERETOPLANT.name) {
                   WhereToPlant()
               }
               composable(route = Routes.MEASURE.name) {
                   MeasurementScreen(
                       temperature = temperature,
                       humidity = humidity,
                       lightLevel = lightLevel,
                       navHostController = navController
                   )
               }
               composable(route = Routes.MYPLANTS.name) {
                   MyPlantsScreen()
               }
               composable(route = Routes.PLANTDB.name) {
                   FindAPlant(navHostController = navController)
               }
               composable(route = Routes.PLANTRECOMMENDATIONS.name) {
                   PlantRecommendationScreen()
               }
           }
       }