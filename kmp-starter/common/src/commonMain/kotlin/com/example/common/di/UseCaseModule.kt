package com.example.common.di

import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.domain.usecase.photo.GetPhotoListUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val useCaseModule: Module = module {
    singleOf(::GetCommentListUseCase)
    singleOf(::GetPhotoListUseCase)
}