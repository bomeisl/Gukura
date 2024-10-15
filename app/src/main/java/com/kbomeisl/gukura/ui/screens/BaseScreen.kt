package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GukuraBaseScreen(
    topAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {

    Scaffold (
        topBar = { topAppBar() },
        content = { content() },

    )
}