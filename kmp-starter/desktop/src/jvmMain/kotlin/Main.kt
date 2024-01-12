import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import main.MainView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-starter",
        state = rememberWindowState(width = 1920.dp, height = 1080.dp).apply {
            isMinimized = false
            position = WindowPosition(Alignment.TopStart)
        },
        icon = BitmapPainter(useResource("drawable/expert_appicon.png", ::loadImageBitmap)),
        resizable = false,
    ) {
        MaterialTheme() {
            MainView()
        }
    }
}