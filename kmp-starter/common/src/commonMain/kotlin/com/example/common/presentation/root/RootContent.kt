package com.example.common.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.example.common.data.dto.TodoItem
import com.example.common.presentation.comment.CommentComponent
import com.example.common.presentation.comment.CommentContent
import com.example.common.presentation.memo.dialog.EditDialog
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.example.common.presentation.memo.MemoComponent
import com.example.common.presentation.memo.MemoContent
import org.koin.java.KoinJavaComponent

@Composable
public fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(component = component)
}

@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.CommentChild -> CommentContent(component = child.component)
            is RootComponent.Child.MemoChild -> MemoContent(component = child.component)
        }
    }
}