package com.kbomeisl.gukura.di

import androidx.room.Room
import com.kbomeisl.gukura.data.database.GukuraDatabase
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.network.PlantNetworkDataSource
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import com.kbomeisl.gukura.data.repository.HomeRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val gukuraAppModule = module {

    single<GukuraDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            GukuraDatabase::class.java,
            "gukuraDatabase"
        ).build()
    }

    single<PlantDao> {
        val gukuraDatabase = get<GukuraDatabase>()
        gukuraDatabase.plantDao()
    }

    single<FindAPlantRepository> {
        FindAPlantRepository(
            plantDao = get<PlantDao>(),
            plantDataSource = PlantNetworkDataSource()
        )
    }

    single<HomeRepository> {
        HomeRepository(
            plantDao = get<PlantDao>(),
            plantDataSource = PlantNetworkDataSource()
        )
    }
}




