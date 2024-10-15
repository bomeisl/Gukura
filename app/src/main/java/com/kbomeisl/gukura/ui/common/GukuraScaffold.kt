package com.kbomeisl.gukura.ui.common

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GukuraScaffold(
    topNavBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { GukuraTopAppBar() },
        content = { content() }
    )
}