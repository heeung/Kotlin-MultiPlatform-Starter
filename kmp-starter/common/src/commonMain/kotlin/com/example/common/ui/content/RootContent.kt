package com.example.common.ui.content

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.common.data.RootStore
import com.example.common.data.dto.TodoItem
import com.example.common.ui.dialog.EditDialog

@Composable
public fun RootContent(modifier: Modifier = Modifier) {
    val model = remember { RootStore() }
    val state = model.state

    MainContent(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        items = state.items,
        inputText = state.inputText,
        onItemClicked = model::onItemClicked,
        onItemDoneChanged = model::onItemDoneChanged,
        onItemDeleteClicked = model::onItemDeleteClicked,
        onAddItemClicked = model::onAddItemClicked,
        onInputTextChanged = model::onInputTextChanged,
    )

    state.editingItem?.also { item ->
        EditDialog(
            item = item,
            onCloseClicked = model::onEditorCloseClicked,
            onTextChanged = model::onEditorTextChanged,
            onDoneChanged = model::onEditorDoneChanged,
        )
    }
}

private val RootStore.RootState.editingItem: TodoItem?
    get() = editingItemId?.let(items::firstById)

private fun List<TodoItem>.firstById(id: Long): TodoItem? =
    firstOrNull { it.id == id }
