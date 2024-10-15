package com.kbomeisl.gukura.ui.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class GukuraTopAppBar @OptIn(ExperimentalMaterial3Api::class) constructor(
    val colors: TopAppBarColors =
                          TopAppBarColors(
                              containerColor = Color.White,
                              scrolledContainerColor = Color.Gray,
                              navigationIconContentColor = Color.White,
                              titleContentColor = Color.White,
                              actionIconContentColor = Color.Gray
                          ),
    val title: String = "Gukura",
    val subtitle: String = "Home",
    val navIcons: List<GukuraNavBarIcon> = listOf(),
    val modifier: Modifier = Modifier
)
