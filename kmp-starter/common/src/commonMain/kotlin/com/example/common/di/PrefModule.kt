package com.example.common.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.common.data.local.preference.SettingRepository

public val prefModule: Module = module {
    singleOf(::SettingRepository)
}