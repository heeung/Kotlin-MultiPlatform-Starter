package com.example.kmpstarter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.example.common.presentation.root.RootComponent
import com.example.common.presentation.root.RootContent
import io.github.aakira.napier.Napier
//import main.RootView
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root =
            RootComponent(
                componentContext = defaultComponentContext(),
            )

        setContent {
            MaterialTheme {
                RootContent(root)
            }
        }
    }
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
