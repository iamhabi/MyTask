import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import group.AddGroup
import group.TaskGroup
import group.createNewTaskGroup
import org.jetbrains.compose.ui.tooling.preview.Preview
import task.AddTask
import task.createNewTaskItem

@Composable
@Preview
fun App() {
    MaterialTheme {
        val taskGroups = remember { mutableStateListOf<TaskGroup>() }
        val selectedIndex = remember { mutableStateOf(-1) }
        
        BoxWithConstraints(Modifier.fillMaxWidth().padding(16.dp)) {
            val isLarge = maxWidth >= 600.dp

            Row {
                if (isLarge) {
                    Column(
                        modifier = Modifier.weight(3.5F),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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

                    Box(
                        modifier = Modifier.weight(0.5F),
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(7F)
                ) {
                    if (!isLarge) {
                        IconButton(
                            onClick = {},
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open group"
                                )
                            }
                        )
                    }

                    Box(modifier = Modifier.weight(1F)) {
                        if (selectedIndex.value != -1) {
                            TaskList(taskGroups[selectedIndex.value].items)
                        }
                    }

                    AddTask { title ->
                        taskGroups[selectedIndex.value].items.add(createNewTaskItem(title))
                    }
                }
            }
        }
    }
}