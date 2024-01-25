package com.example.common.presentation.comment

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.doOnPause
import com.arkivanov.essenty.lifecycle.doOnResume
import com.arkivanov.essenty.lifecycle.subscribe
import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.presentation.comment.intent.CommentEvent
import com.example.common.presentation.comment.state.CommentState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "CommentComponent"
public class CommentComponent(
    componentContext: ComponentContext,
    private val getCommentListUseCase: GetCommentListUseCase = KoinJavaComponent.get(GetCommentListUseCase::class.java)
): KoinComponent, ComponentContext by componentContext {
    private var scope = CoroutineScope(Dispatchers.IO)

    private val events = Channel<CommentEvent>()

    public val state: StateFlow<CommentState> = events
        .receiveAsFlow()
        .runningFold(CommentState(), ::reduceState)
        .stateIn(scope, SharingStarted.Eagerly, CommentState())

    private val _sideEffects = Channel<String>()
    public val sideEffects: Flow<String> = _sideEffects.receiveAsFlow()

    private fun reduceState(current: CommentState, event: CommentEvent): CommentState {
        return when(event) {
            CommentEvent.Loading -> {
                current.copy(loading = true)
            }
            is CommentEvent.Loaded -> {
                current.copy(loading = false, commentList = event.commentList)
            }
        }
    }

    public fun fetchCommentList() {
        scope.launch {
            Napier.d(tag = TAG) { "fetchCommentList()" }
            events.send(CommentEvent.Loading)
            val commentList = getCommentListUseCase.invoke()
            events.send(CommentEvent.Loaded(commentList = commentList))
            _sideEffects.send("${commentList.size} comments loaded")
        }
    }

    private fun refreshCommentList() {
        Napier.d(tag = TAG) { "refreshCommentList -> 새로고침 합니다." }
        fetchCommentList()
    }

    init {
        Napier.d(tag = TAG) { "onCreate" }
        fetchCommentList()
        lifecycle.doOnDestroy { destroy() }
    }

    public fun onClickRefreshButton() {
        refreshCommentList()
    }

    private fun destroy() {
        scope.cancel()
        events.cancel()
        events.close()
        _sideEffects.cancel()
        _sideEffects.close()
        Napier.d(tag = TAG) { "destroy()" }
    }
}