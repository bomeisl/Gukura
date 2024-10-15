package com.kbomeisl.gukura

import android.app.Application
import com.kbomeisl.gukura.di.gukuraAppModule
import org.koin.core.context.GlobalContext.startKoin

class GukuraApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(gukuraAppModule)
        }
    }
}