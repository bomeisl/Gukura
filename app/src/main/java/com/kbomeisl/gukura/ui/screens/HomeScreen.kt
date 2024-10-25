package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.GardenCard
import com.kbomeisl.gukura.ui.common.HomePlantCard
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.testData.gardens
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import java.sql.Time

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
   LaunchedEffect(Unit) {
       homeViewModel.getPlantsInGarden()
   }

    val scrollstate = rememberScrollState()
    val verticalScrollState = rememberScrollState()
    val plantList = homeViewModel.plantList.collectAsState()
    val gardenList = homeViewModel.gardenList
            Surface {
                Row {
                    Column(
                        Modifier
                            .padding(5.dp)
                            .verticalScroll(verticalScrollState),

                    ) {
                        Spacer(modifier = Modifier.height(130.dp))
                        Surface(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(Modifier.horizontalScroll(scrollstate)) {
                                    gardens.gardenList.forEach {
                                        GardenCard(
                                            it,
                                            onClick = { garden ->
                                                homeViewModel.currentGarden.value = garden
                                                homeViewModel.getPlantsInGarden()
                                            }
                                        )
                                    }
                                }
                                plantList.value.forEach {
                                    PlantCard(
                                        it,
                                        gardenList = gardenList.value,
                                        snackbarHostState = snackbarHostState,
                                        addGarden = { plantUi, garden -> homeViewModel.assignGarden(plantUi = plantUi, garden = garden) },
                                        removeGarden = { plantUi, garden -> homeViewModel.removeGarden(plantUi = plantUi, garden = garden) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

}


