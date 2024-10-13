package com.kbomeisl.gukura.ui.models

import androidx.compose.ui.graphics.Color

class GukuraTopNavBar () {
    val title = "Gukura"
    var navSubtitle = "Home"
    var bgColor: Color = Color.White
    var textColor: Color = Color.Black
    var navIcons: List<GukuraNavBarIcon> = listOf()

    private fun switchBgColorOnNav(){
        //when user navigates to a new screen we get a new background color in our nav bar
    }

    private fun removeCurrentNavIcon() {
        //when user is on home screen we shouldn't see the home icon in the nav bar
    }

}
