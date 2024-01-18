package com.example.common.presentation.comment

import com.arkivanov.decompose.ComponentContext
import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.presentation.state.CommentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

public class CommentComponent(
    componentContext: ComponentContext,
    private val getCommentListUseCase: GetCommentListUseCase = KoinJavaComponent.get(GetCommentListUseCase::class.java)
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _commentListState = MutableStateFlow(CommentState(loading = true))
    public val commentListState: SharedFlow<CommentState> = _commentListState.asSharedFlow()

    public fun getCommentList() {
        scope.launch {
            val result = getCommentListUseCase.invoke()

            _commentListState.emit(CommentState(commentList = result))
        }
    }
}