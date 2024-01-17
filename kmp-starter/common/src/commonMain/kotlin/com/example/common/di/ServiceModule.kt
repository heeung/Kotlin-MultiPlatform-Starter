package com.example.common.di

import com.example.common.data.service.CommentService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.get
import org.koin.dsl.module

public val serviceModule: Module = module {
//    single { CommentService(get()) }
    singleOf(::CommentService)
}