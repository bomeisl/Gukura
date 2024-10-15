package com.kbomeisl.gukura.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GukuraScaffold(
    topNavBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { topNavBar },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {  }
            content()
        }
    )
}