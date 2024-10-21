package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.data.repository.MyPlantsRepository
import com.kbomeisl.gukura.data.repository.RecommendationRepository
import com.kbomeisl.gukura.data.repository.PlantRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::HomeRepository)
    singleOf(::FindAPlantRepository)
    singleOf(::MyPlantsRepository)
    singleOf(::RecommendationRepository)
    singleOf(::PlantRepository)
}