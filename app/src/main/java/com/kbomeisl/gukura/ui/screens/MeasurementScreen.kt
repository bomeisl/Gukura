package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.common.GeomagneticSensorCard
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.common.HumiditySensorCard
import com.kbomeisl.gukura.ui.common.SensorCard
import com.kbomeisl.gukura.ui.common.SunlightSensorCard
import com.kbomeisl.gukura.ui.common.TemperatureSensorCard
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MeasurementScreen(
    navHostController: NavHostController,
    measurementViewModel: MeasurementViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    gardenName: String = ""
) {
    val coroutineScope = rememberCoroutineScope()
    val light = measurementViewModel.lightEventListener.light.collectAsStateWithLifecycle()
    val heading = measurementViewModel.heading.collectAsStateWithLifecycle()
    val enableAnimation1 = remember { mutableStateOf(false) }
    val enableAnimation2 = remember { mutableStateOf(false) }
    val animatedFloat by animateDpAsState(
        targetValue = if (enableAnimation1.value) 0.dp else 100.dp,
        animationSpec = tween(durationMillis = 3000)
    )
    val animatedFloat2 by animateFloatAsState(
        targetValue = if (enableAnimation2.value) 1f else 0f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(Unit) {
        enableAnimation1.value = !enableAnimation1.value
        delay(3000)
        enableAnimation2.value = !enableAnimation2.value
    }

    val active = remember { mutableStateOf(false) }
    val spacer: Dp by animateDpAsState(if (active.value) 0.dp else 30.dp)
    LaunchedEffect(Unit) {
        measurementViewModel.initializeLightMeasurements()
        measurementViewModel.populateGardenList()
    }

    Column(
        Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(130.dp))
        SunlightSensorCard(
            lightLevel = measurementViewModel.lightEventListener.light
        )
        Spacer(Modifier.height(30.dp))
        GeomagneticSensorCard(
            magneticOrientation = measurementViewModel.heading
        )
        Spacer(Modifier.height(30.dp))
        Text(
            "Please place your phone in the spot you would like to put your houseplant",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp
        )
            Box() {
                Image(
                    painter = painterResource(R.drawable.window),
                    "",
                    Modifier.size(100.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.plant),
                    "",
                    Modifier.size(100.dp),
                    tint = forestGreen
                )
            }
            Spacer(Modifier.height(animatedFloat))
            Image(painter = painterResource(R.drawable.phone), "", Modifier.size(150.dp))
            Surface(Modifier.alpha(animatedFloat2)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.sensor),
                        "",
                        tint = nightBlue,
                        modifier = Modifier.size(60.dp).clickable {
                            navHostController.navigate(
                                route = "${Routes.PLANTRECOMMENDATIONS.name}/${heading.value.toString()}/${light.value.toString()}"
                            )
                            val direction = measurementViewModel.convertCompassHeadingToDirection(heading.value)
                            if (!direction.isNullOrEmpty()) {
                                measurementViewModel.saveGardenMeasurements(
                                    gardenName = gardenName,
                                    lightLevel = light.value,
                                    compassDirection = direction
                                )
                            } else {
                                measurementViewModel.saveGardenMeasurements(
                                    gardenName = gardenName,
                                    lightLevel = light.value,
                                    compassDirection = ""
                                )
                            }
                        }
                    )
                    Text(
                        "Then hold your phone level, point the top towards the window, and press the above button",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp
                    )
                }
            }
    }
}
