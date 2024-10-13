package com.kbomeisl.gukura.ui.screens

import androidx.compose.runtime.Composable
import com.kbomeisl.gukura.ui.common.GukuraScaffold
import com.kbomeisl.gukura.ui.common.GukuraTopAppBar

@Composable
fun GukuraBaseScreen() {
    GukuraScaffold(
        topNavBar = { GukuraTopAppBar() },
        content = { GukuraNavHost() }
    )
}