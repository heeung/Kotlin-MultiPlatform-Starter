package com.example.kmpstarter.di

import com.example.common.ui.content.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
//    singleOf(::MainViewModel)
    viewModel {
        MainViewModel(get())
    }
}