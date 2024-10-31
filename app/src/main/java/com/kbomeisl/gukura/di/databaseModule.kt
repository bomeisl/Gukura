package com.kbomeisl.gukura.di

import android.content.Context
import androidx.room.Room
import com.kbomeisl.gukura.GukuraApplication
import com.kbomeisl.gukura.data.database.GardenDao
import com.kbomeisl.gukura.data.database.GukuraDatabase
import com.kbomeisl.gukura.data.database.MeasurementDao
import com.kbomeisl.gukura.data.database.PlantDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single<GukuraDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            GukuraDatabase::class.java,
            "gukuraDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<PlantDao> {
        val gukuraDatabase = get<GukuraDatabase>()
        gukuraDatabase.plantDao()
    }

    single<MeasurementDao> {
        val gukuraDatabase = get<GukuraDatabase>()
        gukuraDatabase.measurementDao()
    }

    single<GardenDao> {
        val gukuraDatabase = get<GukuraDatabase>()
        gukuraDatabase.gardenDao()
    }
}