package com.kbomeisl.gukura.ui.models

import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.navigation.Routes

object NavigationIcons {
    val iconWidth = 40.dp
    val iconHeight = 40.dp
    val homeIcon = GukuraNavBarIcon(
        id = R.drawable.house,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Home",
        route = Routes.HOME.name
    )
    val searchIcon = GukuraNavBarIcon(
        id = R.drawable.search,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Search",
        route = "" //TODO()
    )
    val findAPlantIcon = GukuraNavBarIcon(
        id = R.drawable.sun,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Find the perfect plant",
        route = Routes.FINDAPLANT.name
    )
    val whereToPlantIcon = GukuraNavBarIcon(
        id = R.drawable.plant,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Where should I plant this?",
        route = Routes.WHERETOPLANT.name
    )
}