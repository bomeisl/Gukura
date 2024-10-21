package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import java.sql.Time

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navHostController: NavHostController
) {
            Surface {
                Row {
                    Column(Modifier.padding(5.dp)) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Surface(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            shadowElevation = 5.dp,
                        ) {

                        }
                    }
                }
            }

}


