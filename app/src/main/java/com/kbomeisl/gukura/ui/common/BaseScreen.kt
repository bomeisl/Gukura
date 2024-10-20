package com.kbomeisl.gukura.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.screens.GukuraNavHost
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GukuraBaseScreen(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val iconSize = 25.dp
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(.5f)) {
                Column(Modifier.fillMaxHeight()) {
                    TopAppBar(
                        title = {
                            Text(
                                "Gukura",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    )
                    Row(Modifier.padding(10.dp)) {
                        IconButton(
                            modifier = Modifier,
                            enabled = true,
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.plant),
                                    "",
                                    tint = forestGreen,
                                    modifier = Modifier.size(iconSize)
                                )
                            },
                            onClick = {
                                navController.navigate(Routes.FINDAPLANT.name)
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Find the Perfect Plant",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(10.dp)) {
                        IconButton(
                            modifier = Modifier,
                            enabled = true,
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    "",
                                    tint = skyBlue,
                                    modifier = Modifier.size(iconSize)
                                )
                            },
                            onClick = {
                                navController.navigate(Routes.WHERETOPLANT.name)
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Where should I plant this?",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(10.dp)) {
                        IconButton(
                            modifier = Modifier,
                            enabled = true,
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.sensor),
                                    "",
                                    tint = nightBlue,
                                    modifier = Modifier.size(iconSize)
                                )
                            },
                            onClick = {
                                navController.navigate(Routes.MEASURE.name)
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "My plant data",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(10.dp)) {
                        IconButton(
                            modifier = Modifier,
                            enabled = true,
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.heart),
                                    "",
                                    tint = Color.Red,
                                    modifier = Modifier.size(iconSize)
                                )
                            },
                            onClick = {
                                navController.navigate(Routes.MYPLANTS.name)
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "My plants",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }

        },
        modifier = Modifier,
        gesturesEnabled = true,
        scrimColor = Color.LightGray,
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Gukura",
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(5.dp),
                                fontSize = 34.sp
                            )
                        },
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth(),
                        navigationIcon = {
                            IconButton(
                                content = { Icon(Icons.TwoTone.Menu,"") },
                                onClick = { coroutineScope.launch {  drawerState.open() } },
                                modifier = Modifier,
                                enabled = true,
                                colors = IconButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Gray,
                                    disabledContentColor = Color.LightGray,
                                    disabledContainerColor = Color.Transparent
                                )
                            )
                        }
                    )
                },
                modifier = Modifier,
                content = { GukuraNavHost(
                    temperature = temperature,
                    humidity = humidity,
                    lightLevel = lightLevel,
                    navController = navController
                ) }
            )
        }
    )
}