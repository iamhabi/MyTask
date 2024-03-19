import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "MyTask") {
        with (LocalDensity.current) {
            val minSize = DpSize(200.dp, 300.dp).toSize()

            val minWidth = minSize.width.toInt()
            val minHeight = minSize.height.toInt()

            window.minimumSize = Dimension(minWidth, minHeight)
        }

        App()
    }
}