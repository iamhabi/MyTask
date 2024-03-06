package group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskGroupList(
    taskGroups: SnapshotStateList<TaskGroup>,
    selectedIndex: MutableState<Int>
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        itemsIndexed(taskGroups) { index, taskGroup ->
            Box(
                modifier = Modifier.clickable {
                    selectedIndex.value = index
                }
            ) {
                TaskGroupItem(
                    taskGroup = taskGroup,
                    onDeleteItem = { taskGroups.remove(taskGroup) }
                )

                Divider()
            }
        }
    }
}

@Composable
fun TaskGroupItem(
    taskGroup: TaskGroup,
    onDeleteItem: () -> Unit
) {
    val isExpnaded = remember { mutableStateOf(false) }

    val isOpenEdit = remember { mutableStateOf(false) }
    
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskGroup.title,
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
                    DeleteGroup {
                        onDeleteItem()

                        isExpnaded.value = false
                    }

                    EditGroup {
                        isExpnaded.value = false

                        isOpenEdit.value = true
                    }
                }
            )
        }
    }

    when {
        isOpenEdit.value -> {
            
        }
    }
}

@Composable
fun DeleteGroup(
    onDeleteGroup: () -> Unit
) {
    DropdownMenuItem(
        onClick = onDeleteGroup,
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
fun EditGroup(
    onDialogEditGroup: () -> Unit
) {
    DropdownMenuItem(
        onClick = onDialogEditGroup,
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