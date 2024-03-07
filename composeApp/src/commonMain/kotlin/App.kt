import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import group.AddGroup
import group.GroupView
import group.createNewTaskGroup
import org.jetbrains.compose.ui.tooling.preview.Preview
import task.AddTask
import task.TaskList
import task.createNewTaskItem

@Composable
@Preview
fun App() {
    val taskGroups = remember { mutableStateListOf(createNewTaskGroup("Default")) }
    val selectedIndex = remember { mutableStateOf(0) }

    val showGroup = remember { mutableStateOf(false) }

    if (selectedIndex.value != -1) {
        showGroup.value = false
    }

    MaterialTheme {
        BoxWithConstraints(Modifier.fillMaxWidth().padding(16.dp)) {
            val isLarge = maxWidth >= 600.dp

            Row {
                if (isLarge) {
                    Box(
                        modifier = Modifier.weight(4F)
                    ) {
                        GroupView(taskGroups, selectedIndex)
                    }
                }

                Column(
                    modifier = Modifier.weight(7F)
                ) {
                    if (!isLarge) {
                        IconButton(
                            onClick = { showGroup.value = true },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open group"
                                )
                            }
                        )
                    }

                    Box(modifier = Modifier.weight(1F)) {
                        TaskList(taskGroups[selectedIndex.value].items)
                    }

                    AddTask { title ->
                        taskGroups[selectedIndex.value].items.add(createNewTaskItem(title))
                    }
                }
            }
        }
    }

    MaterialTheme {
        when {
            showGroup.value -> {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        IconButton(
                            onClick = { showGroup.value = false },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close group"
                                )
                            }
                        )

                        Box(modifier = Modifier.weight(1F)) {
                            TaskGroupList(
                                taskGroups = taskGroups,
                                selectedIndex = selectedIndex
                            )
                        }

                        AddGroup { title ->
                            taskGroups.add(createNewTaskGroup(title))
                        }
                    }
                }
            }
        }
    }
}