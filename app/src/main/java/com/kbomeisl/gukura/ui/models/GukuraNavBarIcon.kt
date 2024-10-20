package com.kbomeisl.gukura.ui.models

import android.media.Image
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class GukuraNavBarIcon(
    /**
     * Model of a navigation icon to reduce code redundancy
     */
    val imageId: Int,
    val width: Dp = 40.dp,
    val height: Dp = 40.dp,
    val textDescription: String = "",
    val route: String
)
