package com.kbomeisl.gukura.di

import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import org.koin.dsl.module

val findAPlantModule = module {
    factory { FindAPlantRepository() }
}