package com.kbomeisl.gukura.di

import android.hardware.SensorManager
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.data.repository.MyPlantsRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.WeatherRepository
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import com.kbomeisl.gukura.ui.viewmodels.MyPlantsViewModel
import com.kbomeisl.gukura.ui.viewmodels.PlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.WhereToPlantViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(
            get<PlantRepository>(),
            get<GardenRepository>()
        )
    }
    viewModel<FindAPlantViewModel> {
        FindAPlantViewModel(
            get<PlantRepository>(),
            get<GardenRepository>()
        )
    }
    viewModel<MyPlantsViewModel> {
        MyPlantsViewModel(get<PlantRepository>())
    }
    viewModel<MeasurementViewModel> {
        MeasurementViewModel(
            get<MeasurementDao>(),
            get<PlantRepository>(),
            get<GardenRepository>(),
            get<WeatherRepository>()
        )
    }
    viewModel<WhereToPlantViewModel> {
        WhereToPlantViewModel(
            get<GardenRepository>(),
            get<PlantRepository>()
        )
    }
    viewModel<PlantViewModel> {
        PlantViewModel(
            get<PlantRepository>(),
            get<GardenRepository>(),
            get<SensorManager>()
        )
    }
}