package com.kbomeisl.gukura.di

import android.content.Context
import android.hardware.SensorManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sensorModule = module {
    single<SensorManager> {
        androidContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
}