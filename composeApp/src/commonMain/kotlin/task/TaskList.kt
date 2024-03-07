package task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskList(
    taskItems: SnapshotStateList<TaskItem>
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(taskItems) { taskItem ->
            TaskListItem(
                taskItem = taskItem,
                onDeleteItem = { taskItems.remove(taskItem) }
            )

            Divider()
        }
    }
}

@Composable
fun TaskListItem(
    taskItem: TaskItem,
    onDeleteItem: () -> Unit
) {
    val isExpnaded = remember { mutableStateOf(false) }

    val isOpenEdit = remember { mutableStateOf(false) }
    
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

        Box {
            IconButton(
                onClick = { isExpnaded.value = true },
                content = {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Task more click"
                    )
                }
            )
            
            DropdownMenu(
                expanded = isExpnaded.value,
                onDismissRequest = { isExpnaded.value = false },
                content = {
                    DeleteItem {
                        onDeleteItem()

                        isExpnaded.value = false
                    }

                    EditItem {
                        isExpnaded.value = false

                        isOpenEdit.value = true
                    }
                }
            )
        }
    }

    when {
        isOpenEdit.value -> {
            EditTask(
                taskItem = taskItem,
                isOpenEdit = isOpenEdit
            )
        }
    }
}

@Composable
fun DeleteItem(
    onDeleteItem: () -> Unit
) {
    DropdownMenuItem(
        onClick = onDeleteItem,
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Delete")
            }
        }
    )
}

@Composable
fun EditItem(
    onDialogEditTask: () -> Unit
) {
    DropdownMenuItem(
        onClick = onDialogEditTask,
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit title"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Edit")
            }
        }
    )
}