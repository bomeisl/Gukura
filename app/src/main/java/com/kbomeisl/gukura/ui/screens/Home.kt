package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.R
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.common.GukuraTopAppBar
import com.kbomeisl.gukura.ui.models.GukuraNavBarIcon
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    homeViewModel: HomeViewModel = koinViewModel(),
    temperature: MutableStateFlow<Float>,
    humidity: MutableStateFlow<Float> ,
    light: MutableStateFlow<Float>,
    navHostController: NavHostController
) {
    val temperatureState = temperature.collectAsState()
    val humidityState = humidity.collectAsState()
    val lightState = light.collectAsState()
            Surface {
                Row {
                    Column {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text("My Plants", color = Color.Black)
                    }
                }
            }

}


