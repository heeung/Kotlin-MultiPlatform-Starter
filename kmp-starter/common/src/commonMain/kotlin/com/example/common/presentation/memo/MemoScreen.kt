package com.example.common.presentation.memo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.common.data.entity.TodoItem
import com.example.common.presentation.memo.dialog.EditDialog
import com.example.common.util.CustomColor
import com.example.common.util.MARGIN_SCROLLBAR
import com.example.common.util.VerticalScrollbar
import com.example.common.util.onKeyUp
import com.example.common.util.rememberScrollbarAdapter
import com.example.common.util.DBUtil
import com.example.common.util.getMemoList
import com.example.memo.db.Memo
import io.github.aakira.napier.Napier

private const val TAG = "MemoScreen"
@Composable
internal fun MemoScreen(
    component: MemoComponent,
    modifier: Modifier = Modifier,
) {
//    val model = remember { MemoStore() }
//    val state = model.state

    Napier.d(tag = TAG) { "onCreate" }

    val memoListState by component.memoListState.collectAsState()

    Napier.d(tag = TAG) { "$memoListState" }

    MemoView(
        modifier = modifier
            .background(CustomColor.White),
        items = memoListState.memoList,
        inputText = component.inputText,
//        onItemClicked = model::onItemClicked,
        onItemDoneChanged = component::onItemDoneChanged,
//        onItemDeleteClicked = model::onItemDeleteClicked,
        onAddItemClicked = component::onAddItemClicked,
        onInputTextChanged = component::onInputTextChanged,
    )
//    MemoView(
//        modifier = modifier
//            .background(CustomColor.White),
//        items = state.items,
//        inputText = state.inputText,
////        onItemClicked = model::onItemClicked,
//        onItemDoneChanged = model::onItemDoneChanged,
////        onItemDeleteClicked = model::onItemDeleteClicked,
//        onAddItemClicked = model::onAddItemClicked,
//        onInputTextChanged = model::onInputTextChanged,
//    )

//    state.editingItem?.also { item ->
//        EditDialog(
//            item = item,
//            onCloseClicked = model::onEditorCloseClicked,
//            onTextChanged = model::onEditorTextChanged,
//            onDoneChanged = model::onEditorDoneChanged,
//        )
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MemoView(
    modifier: Modifier = Modifier,
    items: List<Memo>,
    inputText: String,
//    onItemClicked: (id: Long) -> Unit,
    onItemDoneChanged: (id: Long, isDone: Boolean) -> Unit,
//    onItemDeleteClicked: (id: Long) -> Unit,
    onAddItemClicked: () -> Unit,
    onInputTextChanged: (String) -> Unit,
) {
    Column(modifier) {
        TopAppBar(title = { Text(text = "Todo List") })

        Box(Modifier.weight(1F)) {
            ListContent(
                items = items,
//                onItemClicked = onItemClicked,
                onItemDoneChanged = onItemDoneChanged,
//                onItemDeleteClicked = onItemDeleteClicked
            )
        }

        Input(
            text = inputText,
            onAddClicked = onAddItemClicked,
            onTextChanged = onInputTextChanged
        )
    }
}

@Composable
private fun ListContent(
    items: List<Memo>,
//    onItemClicked: (id: Long) -> Unit,
    onItemDoneChanged: (id: Long, isDone: Boolean) -> Unit,
//    onItemDeleteClicked: (id: Long) -> Unit,
) {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(items) { item ->
                Item(
                    item = item,
//                    onClicked = { onItemClicked(item.id) },
                    onDoneChanged = { onItemDoneChanged(item.id, it) },
//                    onDeleteClicked = { onItemDeleteClicked(item.id) }
                )

                Divider()
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = listState)
        )
    }
}

@Composable
private fun Item(
    item: Memo,
//    onClicked: () -> Unit,
    onDoneChanged: (Boolean) -> Unit,
//    onDeleteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
//            .clickable(onClick = onClicked)
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        Checkbox(
            checked = if (item.is_done == 0L) false else true,
            modifier = Modifier.align(Alignment.CenterVertically),
            onCheckedChange = onDoneChanged
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = AnnotatedString(item.content),
            modifier = Modifier.weight(1F).align(Alignment.CenterVertically),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
//            onClick = onDeleteClicked
            onClick = {  }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }

        Spacer(modifier = androidx.compose.ui.Modifier.width(MARGIN_SCROLLBAR))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Input(
    text: String,
    onTextChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            modifier = Modifier
                .weight(weight = 1F)
                .onKeyUp(key = Key.Enter, action = onAddClicked),
            onValueChange = onTextChanged,
            label = { Text(text = "Add a todo") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onAddClicked) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}

private val MemoStore.MemoState.editingItem: Memo?
    get() = editingItemId?.let(items::firstById)

private fun List<Memo>.firstById(id: Long): Memo? =
    firstOrNull { it.id == id }