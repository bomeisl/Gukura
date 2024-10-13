package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kbomeisl.gukura.ui.models.GukuraTopNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GukuraTopAppBar(
    //gukuraTopNavBar: GukuraTopNavBar
) {
    Surface {
        Row {
            Text("Hola Mundo!")
        }
    }
}