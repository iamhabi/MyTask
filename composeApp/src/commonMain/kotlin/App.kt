import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val taskGroups = remember { mutableStateListOf<TaskGroup>() }
        val taskItems = remember { mutableStateListOf<TaskItem>() }
        
        Row(Modifier.fillMaxWidth().padding(48.dp)) {
            Column(
                modifier = Modifier.weight(3F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    TaskGroupList(taskGroups)
                }

                AddTask { title ->
                    taskGroups.add(createNewTaskGroup(title))
                }
            }

            Box(
                modifier = Modifier.weight(1F),
                contentAlignment = Alignment.Center
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
            }

            Column(
                modifier = Modifier.weight(7F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    TaskList(taskItems)
                }

                AddTask { title ->
                    taskItems.add(createNewTaskItem(title))
                }
            }
        }
    }
}