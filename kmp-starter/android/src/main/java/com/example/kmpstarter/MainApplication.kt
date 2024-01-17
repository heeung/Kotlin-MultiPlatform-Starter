package com.example.kmpstarter

import android.app.Application
import com.example.common.di.sharedModule
import com.example.kmpstarter.di.viewModelModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(sharedModule + viewModelModule)
        }
        Napier.base(DebugAntilog())
    }
}