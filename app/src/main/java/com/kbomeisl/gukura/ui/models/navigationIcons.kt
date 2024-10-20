package com.kbomeisl.gukura.ui.models

import androidx.compose.ui.unit.dp
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.navigation.Routes

object navigationIcons {
    val iconWidth = 20.dp
    val iconHeight = 20.dp
    val homeIcon = GukuraNavBarIcon(
        imageId = R.drawable.house,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Home",
        route = Routes.HOME.name
    )
    val findAPlantIcon = GukuraNavBarIcon(
        imageId = R.drawable.plant,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Find your plant",
        route = Routes.FINDAPLANT.name
    )
    val whereToPlantIcon = GukuraNavBarIcon(
        imageId = R.drawable.search,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Where should I plant this?",
        route = Routes.WHERETOPLANT.name
    )
    val takeEnvironmentalMeasurements = GukuraNavBarIcon(
        imageId = R.drawable.sensor,
        width = iconWidth,
        height = iconHeight,
        textDescription = "Measure",
        route = Routes.MEASURE.name
    )
    val iconList = listOf(homeIcon,findAPlantIcon,whereToPlantIcon,takeEnvironmentalMeasurements)
}