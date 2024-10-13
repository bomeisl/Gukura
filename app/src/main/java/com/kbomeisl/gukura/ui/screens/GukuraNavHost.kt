package com.kbomeisl.gukura.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kbomeisl.gukura.ui.common.GukuraScaffold
import com.kbomeisl.gukura.ui.common.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.GukuraTopNavBar
import com.kbomeisl.gukura.ui.navigation.Routes

@Composable
fun GukuraNavHost() {
    val navController = rememberNavController()
           NavHost(
               navController = navController,
               startDestination = Routes.FINDAPLANT.name
           ) {
               composable(route = Routes.HOME.name) {
                   Home()
               }
               composable(route = Routes.FINDAPLANT.name) {
                   FindAPlant()
               }
               composable(route = Routes.WHERETOPLANT.name) {
                   WhereToPlant()
               }
           }
       }