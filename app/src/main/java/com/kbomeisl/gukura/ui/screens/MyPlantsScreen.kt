package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.viewmodels.MyPlantsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    myPlantsViewModel: MyPlantsViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        myPlantsViewModel.getPlants()
    }

    Surface {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxHeight()) {
                Spacer(Modifier.height(130.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(R.drawable.heart), "", tint = Color.Red, modifier = Modifier.size(50.dp).padding(10.dp))
                    Text(
                        "My Plants",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Gray
                    )
                }
                myPlantsViewModel.plantList.collectAsState().value.forEach {
                    PlantCard(
                        it
                    )
                }
            }
        }
    }
}