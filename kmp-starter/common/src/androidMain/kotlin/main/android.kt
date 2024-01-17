package main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.ui.content.MainViewModel
import com.example.common.ui.content.RootContent
import org.koin.java.KoinJavaComponent.get

@Composable
public fun RootView(): Unit = RootContent(Modifier.fillMaxSize())