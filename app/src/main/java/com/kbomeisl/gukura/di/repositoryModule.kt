package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.repository.HomeRepository
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::HomeRepository)
    singleOf(::FindAPlantRepository)

}