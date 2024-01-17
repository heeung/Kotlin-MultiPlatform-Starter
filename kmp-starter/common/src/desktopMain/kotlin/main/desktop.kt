package main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.domain.usecase.comment.GetCommentListUseCase
import com.example.common.ui.content.MainViewModel
import com.example.common.ui.content.RootContent

@Composable
public fun RootView(): Unit = RootContent(
    Modifier.fillMaxSize()
)
