package com.kbomeisl.gukura

import android.app.Application
import com.kbomeisl.gukura.di.gukuraAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GukuraApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GukuraApplication)
            modules(gukuraAppModule)
        }
    }
}