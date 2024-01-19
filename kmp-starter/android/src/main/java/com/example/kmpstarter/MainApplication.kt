package com.example.kmpstarter

import android.app.Application
import com.example.common.di.sharedModule
import com.example.common.util.PreferencesUtil
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesUtil.init(applicationContext)

        startKoin {
            androidContext(this@MainApplication)
            modules(sharedModule)
        }

        Napier.base(DebugAntilog())
    }
}