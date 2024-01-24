package com.example.common.presentation.photo

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import com.example.common.domain.usecase.photo.GetPhotoListUseCase
import com.example.common.presentation.photo.state.PhotoState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "PhotoComponent"
public class PhotoComponent(
    componentContext: ComponentContext,
    private val getPhotoListUseCase: GetPhotoListUseCase = KoinJavaComponent.get(GetPhotoListUseCase::class.java)
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _photoListState = MutableStateFlow(PhotoState(loading = true))
    public val photoListState: StateFlow<PhotoState> = _photoListState.asStateFlow()

    public fun getPhotoList() {
        scope.launch {
            val result = getPhotoListUseCase.invoke()

            _photoListState.emit(PhotoState(photoList = result, loading = false))
        }
    }

    init {
        getPhotoList()
    }
}