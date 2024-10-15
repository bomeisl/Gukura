package com.kbomeisl.gukura.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.kbomeisl.gukura.ui.common.GukuraScaffold
import com.kbomeisl.gukura.ui.common.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar

@Composable
fun GukuraBaseScreen() {
    GukuraScaffold(
        topNavBar = { GukuraTopAppBar(gukuraTopAppBar = GukuraTopAppBar()) },
        content = { GukuraNavHost() }
    )
}