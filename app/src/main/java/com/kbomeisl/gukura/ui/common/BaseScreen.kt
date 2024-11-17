package com.kbomeisl.gukura.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.kbomeisl.gukura.ui.navigation.Routes
import com.kbomeisl.gukura.ui.screens.GukuraNavHost
import com.kbomeisl.gukura.ui.theme.forestGreen
import com.kbomeisl.gukura.ui.theme.nightBlue
import com.kbomeisl.gukura.ui.theme.skyBlue
import com.kbomeisl.gukura.ui.viewmodels.PlantViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GukuraBaseScreen(
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float>,
    lightLevel: MutableStateFlow<Float>,
    geomagneticX: MutableStateFlow<Float>,
    geomagneticY: MutableStateFlow<Float>,
    geomagneticZ: MutableStateFlow<Float>
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val iconSize = 25.dp
    var subtitle by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(.5f)) {
                Column(Modifier.fillMaxSize()) {
                    TopAppBar(
                        title = {
                            Row {
                                Text(
                                    "Gukura",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(10.dp),
                                    textAlign = TextAlign.Center
                                )
                                IconButton(
                                    content = {
                                        Image(painter = painterResource(R.drawable.logo),"")
                                    },
                                    onClick = {
                                        navController.navigate(route = Routes.HOME.name)
                                        subtitle = ""
                                    }
                                )
                            }

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
                                subtitle = "Garden Planner"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Garden Planner",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp).clickable {
                                navController.navigate(Routes.FINDAPLANT.name)
                                subtitle = "Garden Planner"
                                coroutineScope.launch { drawerState.close() }
                            }
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
                                subtitle = "Where to Plant"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Where Should I Plant This?",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp).clickable {
                                navController.navigate(Routes.WHERETOPLANT.name)
                                subtitle = "Where to Plant"
                                coroutineScope.launch { drawerState.close() }
                            }
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
                                subtitle = "Measurements"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Which Plants Will Thrive Here?",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp).clickable {
                                navController.navigate("${Routes.MEASURE.name}/")
                                subtitle = "Measurements"
                                coroutineScope.launch { drawerState.close() }
                            }
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
                                subtitle = "My Plant Wishlist"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "My Plant Wishlist",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp).clickable {
                                navController.navigate(Routes.MYPLANTS.name)
                                subtitle = "My Plant Wishlist"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                    }
                    Row(Modifier.padding(10.dp)) {
                        IconButton(
                            modifier = Modifier,
                            enabled = true,
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.database),
                                    "",
                                    tint = forestGreen,
                                    modifier = Modifier.size(iconSize)
                                )
                            },
                            onClick = {
                                navController.navigate(Routes.PLANTDB.name)
                                subtitle = "Database"
                                coroutineScope.launch { drawerState.close() }
                            }
                        )
                        Text(
                            "Plant Database",
                            color = Color.Gray,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(10.dp).clickable {
                                navController.navigate(Routes.PLANTDB.name)
                                subtitle = "Database"
                                coroutineScope.launch { drawerState.close() }
                            }
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
                            Row(Modifier.fillMaxWidth()) {
                                IconButton(
                                    content = {
                                        Image(painter = painterResource(R.drawable.logo),"")
                                    },
                                    onClick = {
                                        navController.navigate(route = Routes.HOME.name)
                                        subtitle = ""
                                    }
                                )
                                Text(
                                    text = "Gukura",
                                    fontFamily = FontFamily.Monospace,
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier.padding(5.dp),
                                    fontSize = 30.sp
                                )

                                Spacer(Modifier.width(60.dp))
                                Text(
                                    subtitle,
                                    modifier = Modifier.padding(10.dp),
                                    fontWeight = FontWeight.Light,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.End
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(0.dp),
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
                content = {
                    GukuraNavHost(
                        temperature = temperature,
                        humidity = humidity,
                        lightLevel = lightLevel,
                        navController = navController,
                        geomaneticX = geomagneticX,
                        geomaneticY = geomagneticY,
                        geomaneticZ = geomagneticZ,
                        snackbarHostState = snackBarHostState,
                    )
                          },
                snackbarHost = {
                    SnackbarHost(
                        snackBarHostState,
                        modifier = Modifier.padding(5.dp),
                        snackbar = {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Snackbar(
                                    snackbarData = it,
                                    shape = RoundedCornerShape(30),
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    )
                }
            )
        }
    )
}