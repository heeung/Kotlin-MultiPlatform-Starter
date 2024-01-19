package com.example.common.di

import com.example.common.data.service.CommentService
import com.example.common.data.service.PhotoService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val serviceModule: Module = module {
    singleOf(::CommentService)
    singleOf(::PhotoService)
}