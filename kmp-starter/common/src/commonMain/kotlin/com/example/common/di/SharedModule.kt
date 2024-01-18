package com.example.common.di

import org.koin.core.module.Module

public val sharedModule: List<Module> = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    serviceModule
)