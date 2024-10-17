package com.kbomeisl.gukura.ui.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kbomeisl.gukura.ui.theme.backgroundTAB
import com.kbomeisl.gukura.ui.theme.navIconInsetTAB

class GukuraTopAppBar(
    /**
     * The top app bar's state holder
     */
    val title: String = "Gukura",
    val subtitle: String = "Home",
    val navIcons: List<GukuraNavBarIcon> = listOf(),
    val modifier: Modifier = Modifier
) {
    companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        val colors = TopAppBarColors(
            containerColor = backgroundTAB,
            navigationIconContentColor = navIconInsetTAB,
            scrolledContainerColor = backgroundTAB,
            actionIconContentColor = Color.Gray,
            titleContentColor = backgroundTAB
        )
    }
}
