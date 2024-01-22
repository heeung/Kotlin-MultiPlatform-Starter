package com.example.common.presentation.memo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.example.common.presentation.comment.state.CommentState
import com.example.common.presentation.memo.state.MemoState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import com.example.common.util.DBUtil
import com.example.common.util.getMemoList
import com.example.memo.db.Memo
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

private const val TAG = "MemoComponent"
public class MemoComponent (
    componentContext: ComponentContext
) : KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val queries = DBUtil.memoQueries

    private val _memoListState = MutableStateFlow(MemoState(loading = true))
    public val memoListState: StateFlow<MemoState> = _memoListState.asStateFlow()

    internal var inputText by mutableStateOf("")

    internal fun onInputTextChanged(text: String) {
        inputText = text
    }

    internal fun onAddItemClicked() {
        insertMemo(
            content = inputText,
            isDone = false
        )
    }

    internal fun onItemDoneChanged(id: Long, isDone: Boolean) {
        _memoListState.value.memoList[id.toInt() - 1].updateMemo(
            isDone = isDone
        )
    }

    public fun Memo.updateMemo(content: String = this.content, isDone: Boolean = this.is_done != 0L) {
        queries.updateMemo(
            id = this.id,
            content = content,
            is_done = if (isDone) 1L else 0L
        )

        getMemoList()
    }

    public fun insertMemo(content: String, isDone: Boolean) {
        queries.insertMemo(content = content, is_done = if (isDone) 1L else 0L)

        getMemoList()
    }

    private fun getMemoList() {
        val list = queries.getMemoList()
        scope.launch {
            _memoListState.emit(MemoState(memoList = list, loading = false))
        }
        Napier.d(tag = TAG) { "getMemoList : $list" }
    }

    init {
        getMemoList()
    }
}