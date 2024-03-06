import kotlin.random.Random

data class TaskGroup(
    val id: Long,
    var title: String
)

fun createNewTaskGroup(title: String): TaskGroup {
    return TaskGroup(Random.nextLong(), title)
}