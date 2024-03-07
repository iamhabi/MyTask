package task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.datetime.LocalDateTime
import kotlin.random.Random

data class TaskItem(
    val id: Long,
    var isDone: MutableState<Boolean>,
    var title: String,
    var description: String,
    val dueDate: LocalDateTime?
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