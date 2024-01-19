package com.example.common.presentation.memo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.common.data.entity.TodoItem

internal class MemoStore {

    var state: MemoState by mutableStateOf(initialState())
        private set

    fun onItemClicked(id: Long) {
        setState { copy(editingItemId = id) }
    }

    fun onItemDoneChanged(id: Long, isDone: Boolean) {
        setState {
            updateItem(id = id) { it.copy(isDone = isDone) }
        }
    }

    fun onItemDeleteClicked(id: Long) {
        setState { copy(items = items.filterNot { it.id == id }) }
    }

    fun onAddItemClicked() {
        setState {
            val newItem =
                TodoItem(
                    id = items.maxOfOrNull(TodoItem::id)?.plus(1L) ?: 1L,
                    text = inputText,
                )

            copy(items = items + newItem, inputText = "")
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
            updateItem(id = requireNotNull(editingItemId)) { it.copy(text = text) }
        }
    }

    fun onEditorDoneChanged(isDone: Boolean) {
        setState {
            updateItem(id = requireNotNull(editingItemId)) { it.copy(isDone = isDone) }
        }
    }

    private fun MemoState.updateItem(id: Long, transformer: (TodoItem) -> TodoItem): MemoState =
        copy(items = items.updateItem(id = id, transformer = transformer))

    private fun List<TodoItem>.updateItem(id: Long, transformer: (TodoItem) -> TodoItem): List<TodoItem> =
        map { item -> if (item.id == id) transformer(item) else item }

    private fun initialState(): MemoState =
        MemoState(
            items = (1L..5L).map { id ->
                TodoItem(id = id, text = "Some text $id")
            }
        )

    private inline fun setState(update: MemoState.() -> MemoState) {
        state = state.update()
    }

    data class MemoState(
        val items: List<TodoItem> = emptyList(),
        val inputText: String = "",
        val editingItemId: Long? = null,
    )
}