import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlin.random.Random

data class TaskGroup(
    val id: Long,
    var title: String,
    val items: SnapshotStateList<TaskItem>
)

fun createNewTaskGroup(title: String): TaskGroup {
    return TaskGroup(Random.nextLong(), title, mutableStateListOf())
}