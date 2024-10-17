package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.ui.models.NavigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel: ViewModel() {
    val homeTopAppBar: GukuraTopAppBar = GukuraTopAppBar(
        title = "Gukura",
        subtitle = "Home",
        navIcons = listOf(
            NavigationIcons.searchIcon,
            NavigationIcons.findAPlantIcon,
            NavigationIcons.whereToPlantIcon,
            )
    )
    val temperature = MutableStateFlow<Float>(0F)
    val myPlantList = listOf<PlantUi>()
}