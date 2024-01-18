package com.example.common.presentation.memo

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

public class MemoComponent (
    componentContext: ComponentContext
) : KoinComponent, ComponentContext by componentContext {

}