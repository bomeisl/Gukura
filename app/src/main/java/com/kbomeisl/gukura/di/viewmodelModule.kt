package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.ui.screens.Home
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(get<HomeRepository>())
    }
    viewModel<FindAPlantViewModel> {
        FindAPlantViewModel(get<FindAPlantRepository>())
    }
}