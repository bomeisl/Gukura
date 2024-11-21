package com.kbomeisl.gukura.di

import android.hardware.SensorManager
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.LocationRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.WeatherRepository
import com.kbomeisl.gukura.ui.viewmodels.FindAPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.GardenPlannerViewModel
import com.kbomeisl.gukura.ui.viewmodels.HomeViewModel
import com.kbomeisl.gukura.ui.viewmodels.MeasurementViewModel
import com.kbomeisl.gukura.ui.viewmodels.MyPlantsViewModel
import com.kbomeisl.gukura.ui.viewmodels.PlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.GrowthViewModel
import com.kbomeisl.gukura.ui.viewmodels.WhereToPlantViewModel
import com.kbomeisl.gukura.ui.viewmodels.WishListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(
        )
    }
    viewModel<FindAPlantViewModel> {
        FindAPlantViewModel()
    }
    viewModel<MyPlantsViewModel> {
        MyPlantsViewModel(get<PlantRepository>())
    }
    viewModel<MeasurementViewModel> {
        MeasurementViewModel(
            get<MeasurementDao>(),
            weatherRepository =get<WeatherRepository>(),
            locationRepository = get<LocationRepository>(),
        )
    }
    viewModel<WhereToPlantViewModel> {
        WhereToPlantViewModel()
    }
    viewModel<PlantViewModel> {
        PlantViewModel(
            get<PlantRepository>(),
            get<GardenRepository>(),
            get<SensorManager>()
        )
    }
    viewModel<GardenPlannerViewModel> {
        GardenPlannerViewModel()
    }
    viewModel<GrowthViewModel> {
        GrowthViewModel()
    }
    viewModel<WishListViewModel>() {
        WishListViewModel()
    }
}