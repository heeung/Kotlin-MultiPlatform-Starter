package com.example.common.di

import com.example.common.ui.content.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val viewModelModule: Module = module {
//    single { MainViewModel(get()) }
    singleOf(::MainViewModel)
}