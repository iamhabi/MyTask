package task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

data class TaskItem(
    val id: Long,
    var isDone: MutableState<Boolean>,
    var title: String,
    var description: String,
    var dueDate: Long?
)

fun createNewTaskItem(title: String): TaskItem {
    return TaskItem(
        id = Random.nextLong(),
        isDone = mutableStateOf(false),
        title = title,
        description = "",
        dueDate = null
    )
}