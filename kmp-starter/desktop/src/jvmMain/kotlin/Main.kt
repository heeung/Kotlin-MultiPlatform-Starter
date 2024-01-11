import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import com.example.common.UIShow

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-starter",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
        MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
//            Show()
            UIShow()
        }
    }
}

@Composable
fun Show() {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.ui.graphics.Color.Blue
    ) {

    }
}