package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.coroutines.launch

@Composable
fun PlantCard(
    plantUi: PlantUi,
    gardenUi: GardenUi = GardenUi(),
    snackbarHostState: SnackbarHostState
) {
    val heartColorState = remember { mutableStateOf(Color.Gray) }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.padding(15.dp),
        shadowElevation = 5.dp,
        shape = RectangleShape,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    plantUi.name,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(5.dp)
                )
                Row {
                    AsyncImage(
                        model = plantUi.imageUrl,
                        contentDescription = plantUi.description,
                        contentScale = ContentScale.Crop,
                        clipToBounds = true,
                        modifier = Modifier.size(150.dp).padding(5.dp)
                    )
                    Column {
                        Text(plantUi.temperature+" °C", fontFamily = FontFamily.Monospace)
                        Text(plantUi.humidity+" %", fontFamily = FontFamily.Monospace)
                        Text(plantUi.lightLevel+" lux", fontFamily = FontFamily.Monospace)
                        Spacer(Modifier.height(30.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                content = {
                                    Icon(
                                        painter = painterResource(R.drawable.heart),
                                        "",
                                        tint = heartColorState.value,
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                onClick = {
                                    heartColorState.value = Color.Red
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "${plantUi.name} wishlisted for ${gardenUi.name}",
                                            actionLabel = "Undo",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}