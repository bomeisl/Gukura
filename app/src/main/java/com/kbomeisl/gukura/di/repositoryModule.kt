package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.repository.RecommendationRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.data.repository.GardenRepository
import com.kbomeisl.gukura.data.repository.WeatherRepository
import com.kbomeisl.gukura.data.repository.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::RecommendationRepository)
    singleOf(::PlantRepository)
    singleOf(::GardenRepository)
    singleOf(::WeatherRepository)
    single<LocationRepository> {
        LocationRepository(androidContext())
    }
}