import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.example.common.di.sharedModule
import com.example.common.ui.content.MainViewModel
import di.viewModelModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import main.RootView
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

fun main() {
    startKoin {
        modules(sharedModule + viewModelModule)
    }
    Napier.base(DebugAntilog())

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "kmp-starter",
            state = rememberWindowState(width = 1760.dp, height = 990.dp).apply {
                isMinimized = false
                position = WindowPosition(Alignment.TopStart)
            },
            icon = BitmapPainter(useResource("drawable/expert_appicon.png", ::loadImageBitmap)),
            resizable = false,
        ) {
            MaterialTheme() {
                Screen()
//                RootView()
            }
        }
    }
}

@Composable
fun Screen(
    viewModel: MainViewModel = KoinJavaComponent.get(MainViewModel::class.java)
) {
    val commentListState = viewModel.commentListState.collectAsState(false)
    viewModel.getCommentList()
    Napier.v { commentListState.value.toString() }

    RootView()
}