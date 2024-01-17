package di

import com.example.common.ui.content.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    singleOf(::MainViewModel)
}