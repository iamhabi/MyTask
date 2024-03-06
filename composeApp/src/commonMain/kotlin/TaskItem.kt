import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

data class TaskItem(
    val id: Long,
    var title: String,
    var isDone: MutableState<Boolean>
)

fun createNewTaskItem(title: String): TaskItem {
    return TaskItem(Random.nextLong(), title, mutableStateOf(false))
}