package com.kbomeisl.gukura.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kbomeisl.gukura.ui.navigation.Routes

@Composable
fun GukuraNavHost() {
    val navController = rememberNavController()
           NavHost(
               navController = navController,
               startDestination = Routes.HOME.name
           ) {
               composable(route = Routes.HOME.name) {
                   //Home()
               }
               composable(route = Routes.FINDAPLANT.name) {
                   FindAPlant()
               }
               composable(route = Routes.WHERETOPLANT.name) {
                   WhereToPlant()
               }
           }
       }