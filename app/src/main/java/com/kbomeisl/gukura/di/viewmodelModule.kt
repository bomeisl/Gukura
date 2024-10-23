package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.data.repository.MyPlantsRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import com.kbomeisl.gukura.ui.viewmodels.MyPlantsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(get<HomeRepository>())
    }
    viewModel<FindAPlantViewModel> {
        FindAPlantViewModel(get<PlantRepository>())
    }
    viewModel<MyPlantsViewModel> {
        MyPlantsViewModel(get<MyPlantsRepository>())
    }
    viewModel<MeasurementViewModel> {
        MeasurementViewModel(
            get<MeasurementDao>(),
            get<PlantRepository>(),
            get<GardenRepository>()
        )
    }
}