package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FindAPlant() {
    Surface {
        Row {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "Find a Plant", color = Color.Black)
            }
        }
    }
}