package com.kbomeisl.gukura.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.kbomeisl.gukura.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    Image(painter = painterResource(R.drawable.sun),"")
}


