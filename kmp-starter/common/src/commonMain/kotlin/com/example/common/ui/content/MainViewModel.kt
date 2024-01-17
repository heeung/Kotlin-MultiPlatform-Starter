package com.example.common.ui.content

import com.example.common.base.BaseViewModel
import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.ui.state.CommentState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

public class MainViewModel(
    private val getCommentListUseCase: GetCommentListUseCase
): BaseViewModel() {
    private val _commentListState = MutableStateFlow(CommentState(loading = true))
    public val commentListState: SharedFlow<CommentState> = _commentListState.asSharedFlow()

    public fun getCommentList() {
        scope.launch {
            val result = getCommentListUseCase.invoke()

            _commentListState.emit(CommentState(commentList = result))
        }
    }
}