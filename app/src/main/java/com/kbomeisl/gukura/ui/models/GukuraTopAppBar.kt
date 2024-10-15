package com.kbomeisl.gukura.ui.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class GukuraTopAppBar() {
    /**
     * The UI stateholder for the top app bar. Used to better organize, abstract, and modularize the
     * UI state of the top app bar for use by viewmodels.
     */
    val title = "Gukura"
    var navSubtitle = "Home"
    @OptIn(ExperimentalMaterial3Api::class)
    var colors: TopAppBarColors =
        TopAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.Gray,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Gray
        )
    var navIcons: List<GukuraNavBarIcon> = listOf()
    var modifier: Modifier = Modifier

    private fun switchBgColorOnNav(){
        //TODO(change the top app bar color to the correct color based on which screen we are on)
    }

    private fun removeCurrentNavIcon() {
        //TODO(when user is on home screen, say, we shouldn't see the home icon in the nav bar)
    }

}
