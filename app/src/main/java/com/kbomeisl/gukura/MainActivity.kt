package com.kbomeisl.gukura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kbomeisl.gukura.ui.common.GukuraBaseScreen
import com.kbomeisl.gukura.ui.screens.GukuraNavHost
import com.kbomeisl.gukura.ui.theme.GukuraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GukuraTheme {
                GukuraNavHost()
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
}