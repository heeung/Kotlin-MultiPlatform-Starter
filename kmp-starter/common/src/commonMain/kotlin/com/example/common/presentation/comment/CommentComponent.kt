package com.example.common.presentation.comment

import com.arkivanov.decompose.ComponentContext
import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.presentation.comment.state.CommentState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "CommentComponent"
public class CommentComponent(
    componentContext: ComponentContext,
    private val getCommentListUseCase: GetCommentListUseCase = KoinJavaComponent.get(GetCommentListUseCase::class.java)
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _commentListState = MutableStateFlow(CommentState(loading = true))
    public val commentListState: StateFlow<CommentState> = _commentListState.asStateFlow()

    public fun getCommentList() {
        scope.launch {
            val result = getCommentListUseCase.invoke()

            _commentListState.emit(CommentState(commentList = result, loading = false))
        }
    }

    private fun refreshCommentList() {
        Napier.d(tag = TAG) { "refreshCommentList -> 새로고침 합니다." }
        scope.launch {
            _commentListState.emit(CommentState(commentList = emptyList(), loading = true))

            val result = getCommentListUseCase.invoke()

            _commentListState.emit(CommentState(commentList = result, loading = false))
        }
    }

    init {
        Napier.d(tag = TAG) { "onCreate" }
        getCommentList()
    }

    public fun onClickRefreshButton() {
        refreshCommentList()
    }
}