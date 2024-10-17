package com.kbomeisl.gukura.di

import androidx.room.Room
import com.kbomeisl.gukura.data.database.GukuraDatabase
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.network.PlantDataSource
import com.kbomeisl.gukura.data.repository.FindAPlantRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import kotlin.math.sin

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

    factory<FindAPlantRepository> {
        FindAPlantRepository(
            plantDao = get<PlantDao>(),
            plantDataSource = PlantDataSource()
        )
    }

}




