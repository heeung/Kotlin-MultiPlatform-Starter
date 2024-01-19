package com.example.common.di

import com.example.common.domain.repository.CommentRepository
import com.example.common.data.repository.CommentRepositoryImpl
import com.example.common.domain.repository.PhotoRepository
import com.example.common.data.repository.PhotoRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val repositoryModule: Module = module {
    singleOf(::CommentRepositoryImpl) bind CommentRepository::class
    singleOf(::PhotoRepositoryImpl) bind PhotoRepository::class
}