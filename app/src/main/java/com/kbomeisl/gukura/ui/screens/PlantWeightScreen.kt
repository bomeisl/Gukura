package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.viewmodels.WeightViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantWeightScreen(
    plantWeightViewModel: WeightViewModel = koinViewModel()
) {
    Surface() {
        Column {
            Icon(
                painter = painterResource(R.drawable.plant),
                "" ,
                tint = forestGreen
            )
            Image(
                painter = painterResource(R.drawable.scale),
                ""
            )

        }
    }
}