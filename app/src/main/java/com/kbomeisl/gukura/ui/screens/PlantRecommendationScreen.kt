package com.kbomeisl.gukura.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.ui.common.PlantCard
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.models.toDb
import com.kbomeisl.gukura.ui.theme.sunOrange
import com.kbomeisl.gukura.ui.viewmodels.GardenPlannerViewModel
import io.ktor.util.reflect.typeInfo
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantRecommendationScreen(
    heading: String,
    light: String,
    gardenName: String,
    gardenPlannerViewModel: GardenPlannerViewModel = koinViewModel<GardenPlannerViewModel>(),
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        gardenPlannerViewModel.getRecommendedPlants(sunlight = light.toFloat(), heading = heading.toFloat())
    }
    val plantList = gardenPlannerViewModel.plantListDb.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Surface(Modifier.fillMaxSize()){
        Row{
            Column(Modifier.verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(130.dp))
                gardenPlannerViewModel.convertCompassHeadingToDirection(heading.toFloat())
                    ?.let {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(R.drawable.window),"", Modifier.size(30.dp))
                            Text(it + " facing window", fontFamily = FontFamily.Monospace)
                        }

                    }
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.sun_2),"", Modifier.size(30.dp), tint = sunOrange)
                    Text(light + " lux", fontFamily = FontFamily.Monospace)
                }
                Text("Here are some plant recommendations!", color = Color.Gray)
                plantList.value
                    .filter {
                        (it.minLightLevel.toFloat() <= light.toFloat()
                            &&
                                light.toFloat() <= it.maxLightLevel.toFloat())
                    }
                    .filter {
                        val compassDirection = gardenPlannerViewModel.convertCompassHeadingToDirection(heading.toFloat())
                        it.directions.split(",").contains(compassDirection)
                    }
                    .map { it.toUi() }
                    .forEach {
                    PlantCard(
                        it,
                        snackbarHostState = snackbarHostState,
                        addGarden = {gardenUi: String, plantUi: PlantUi ->
                            gardenPlannerViewModel.addGardenToPlant(
                                plantUi = it,
                                gardenName = gardenName)
                                    },
                        clearGarden = {gardenUi: String, plantUi: PlantUi ->  },
                        showGardenStats = false,
                        gardenList = gardenPlannerViewModel.gardenList
                    )
                }
            }
        }
    }
}