package com.kbomeisl.gukura.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class FindAPlantViewModel(): ViewModel() {
    val temperature = MutableStateFlow<Float>(0F)
}