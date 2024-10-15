package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.ui.models.NavigationIcons
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
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
}