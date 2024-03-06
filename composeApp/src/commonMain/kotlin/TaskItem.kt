import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

data class TaskItem(
    val id: Long,
    var title: MutableState<String>,
    var isDone: MutableState<Boolean>
)

fun createNewTaskItem(title: String): TaskItem {
    return TaskItem(Random.nextLong(), mutableStateOf(title), mutableStateOf(false))
}