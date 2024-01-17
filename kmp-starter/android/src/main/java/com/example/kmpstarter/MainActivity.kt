package com.example.kmpstarter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.common.ui.content.MainViewModel
import io.github.aakira.napier.Napier
import main.RootView
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

//    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
//                RootView()
//                Screen(viewModel)
                Screen()
            }
        }
    }
}

@Composable
fun Screen(
    viewModel: MainViewModel = getViewModel()
) {
    val commentListState = viewModel.commentListState.collectAsState(false)
    viewModel.getCommentList()
    Napier.d { commentListState.value.toString() }

    RootView()
}

@Composable
fun CustomMaterialTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
        }

        darkTheme -> {}
        else -> {}
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            activity.window.statusBarColor = Color.GRAY
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        typography = MaterialTheme.typography,
        content = content
    )
}
