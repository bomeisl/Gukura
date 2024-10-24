package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.kbomeisl.gukura.ui.common.GardenCard
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
    navHostController: NavHostController
) {
    val scrollstate = rememberScrollState()
            Surface {
                Row {
                    Column(Modifier.padding(5.dp)) {
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
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(R.drawable.flowers_pot_of_yard),
                                        "",
                                        tint = forestGreen,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .padding(5.dp)
                                    )
                                    Text(
                                        "My Gardens",
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(5.dp),
                                        fontSize = 20.sp
                                    )
                                }

                                Text("Average Environmental Measurements", color = Color.Gray, fontFamily = FontFamily.Monospace)

                                Row(Modifier.horizontalScroll(scrollstate)) {
                                    gardens.gardenList.forEach {
                                        GardenCard(it)
                                    }
                                }
                            }
                        }

                        Surface(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Column {
                                Row {
                                    Icon(
                                        painter = painterResource(R.drawable.heart),
                                        "",
                                        Modifier
                                            .size(40.dp)
                                            .padding(5.dp),
                                        tint = Color.Red
                                    )
                                    Text(
                                        "My Wishlist",
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                                Row {

                                }
                            }
                        }
                    }
                }
            }

}


