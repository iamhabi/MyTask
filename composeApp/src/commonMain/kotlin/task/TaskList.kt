package task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskList(
    taskItems: SnapshotStateList<TaskItem>,
    onItemClick: (TaskItem) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(taskItems) { taskItem ->
            Column(modifier = Modifier.clickable { onItemClick(taskItem) }) {
                TaskListItem(
                    taskItem = taskItem,
                    onDeleteItem = { taskItems.remove(taskItem) }
                )

                Divider()
            }
        }
    }
}

@Composable
fun TaskListItem(
    taskItem: TaskItem,
    onDeleteItem: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = taskItem.isDone.value,
            onCheckedChange = { taskItem.isDone.value = !taskItem.isDone.value }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = taskItem.title,
            modifier = Modifier.weight(1F)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = onDeleteItem,
            content = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task",
                    tint = Color.Red
                )
            }
        )
    }
}