package com.kbomeisl.gukura.ui.models

import androidx.compose.ui.unit.Dp

data class GukuraNavBarIcon(
    /**
     * Model of a navigation icon to reduce code redundancy
     */
    val id: Int,
    val width: Dp,
    val height: Dp,
    val textDescription: String,
    val route: String
)
