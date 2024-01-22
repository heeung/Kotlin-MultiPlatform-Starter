package com.example.common.presentation.memo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.common.data.entity.TodoItem
import com.example.memo.db.Memo
import com.example.common.util.DBUtil
import com.example.common.util.getMemoList
import io.github.aakira.napier.Napier

internal class MemoStore {

    var state: MemoState by mutableStateOf(initialState())
        private set

    fun onItemClicked(id: Long) {
        setState { copy(editingItemId = id) }
    }

    fun onItemDoneChanged(id: Long, isDone: Boolean) {
        setState {
            updateItem(id = requireNotNull(editingItemId), if (isDone) 1L else 0L, items[editingItemId.toInt()].content)
        }
    }

    fun onItemDeleteClicked(id: Long) {
        setState { copy(items = items.filterNot { it.id == id }) }
    }

    fun onAddItemClicked() {
        setState {
//            val newItem =
//                Memo(
//                    id = items.maxOfOrNull(TodoItem::id)?.plus(1L) ?: 1L,
//                    content = inputText,
//                    is_done = 0
//                )

            DBUtil.memoQueries.insertMemo(
                content = inputText,
                is_done = 0
            )

            val list = DBUtil.memoQueries.getMemoList()

            copy(items = list, inputText = "")
        }
    }

    fun onInputTextChanged(text: String) {
        setState { copy(inputText = text) }
    }

    fun onEditorCloseClicked() {
        setState { copy(editingItemId = null) }
    }

    fun onEditorTextChanged(text: String) {
        setState {
            updateItem(id = requireNotNull(editingItemId), items[editingItemId.toInt()].is_done, content = text)
        }
    }

    fun onEditorDoneChanged(isDone: Long) {
        setState {
            updateItem(id = requireNotNull(editingItemId), isDone, items[editingItemId.toInt()].content)
        }
    }

    private fun MemoState.updateItem(id: Long, is_done: Long, content: String): MemoState {
        DBUtil.memoQueries.updateMemo(id = id, is_done = is_done, content = content)

        return initialState()
    }

//    private fun List<Memo>.updateItem(id: Long, transformer: (Memo) -> Memo): List<Memo> =
//        map { item -> if (item.id == id) transformer(item) else item }

    private fun initialState(): MemoState {
        val memoList: List<Memo> = DBUtil.memoQueries.getMemoList()

        Napier.d  { "initialStated : $memoList" }

        return MemoState(
            items = memoList
        )
    }

    private inline fun setState(update: MemoState.() -> MemoState) {
        state = state.update()
    }

    data class MemoState(
        val items: List<Memo> = emptyList(),
        val inputText: String = "",
        val editingItemId: Long? = null,
    )
}