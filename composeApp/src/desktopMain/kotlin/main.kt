import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "MyTask") {
        window.minimumSize = Dimension(400, 600)

        App()
    }
}