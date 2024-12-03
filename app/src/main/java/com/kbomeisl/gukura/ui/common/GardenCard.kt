package com.kbomeisl.gukura.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GardenUi
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.theme.add
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.sunOrange

@Composable
fun GardenCard(
    gardenUi: GardenUi,
    onClick: () -> Unit,
    removeGarden: () -> Unit,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() },
        shadowElevation = 3.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(gardenUi.name, textAlign = TextAlign.Center, fontFamily = FontFamily.Default)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(gardenUi.avgLightLevel.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.sun_2),
                            "",
                            Modifier.size(10.dp).align(Alignment.CenterVertically),
                            tint = sunOrange
                        )
                        Text(gardenUi.avgLightLevel + " lux", fontFamily = FontFamily.Monospace)
                    }
                }
                AnimatedVisibility(gardenUi.windowDirection.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.window2),
                            "",
                            Modifier.size(10.dp).align(Alignment.CenterVertically)
                        )
                        Text(
                            gardenUi.windowDirection + " facing window",
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

            }
            Row {
                Column(Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    IconButton(
                        content = { Icon(Icons.Outlined.Clear, "", tint = Color.Red) },
                        onClick = { removeGarden() }
                    )
                    Text("Remove Garden", fontFamily = FontFamily.Monospace, fontSize = 10.sp)
                }
                Column(Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.trowel),
                                "",
                                tint = add
                            )
                        },
                        onClick = {
                            navController.navigate("${Routes.MEASURE.name}/${gardenUi.name}")
                        }
                    )
                    Text("Plan Your Garden", fontFamily = FontFamily.Monospace, fontSize = 10.sp)
                }
                Column(Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.progress),
                                "",
                                tint = nightBlue
                            )
                        },
                        onClick = {
                            navController.navigate("${Routes.GROWTH.name}/${gardenUi.name}")
                        }
                    )
                    Text("Plant Growth", fontFamily = FontFamily.Monospace, fontSize = 10.sp)
                }
            }
        }
    }
}