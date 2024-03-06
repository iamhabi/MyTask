import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val items = remember { mutableStateListOf<TaskItem>() }
        
        Column(Modifier.fillMaxWidth().padding(48.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.weight(1F)) {
                TaskList(items)
            }
            
            AddTask { title ->
                items.add(createNewTaskItem(title))
            }
        }
    }
}

@Composable
fun AddTask(
    onCreateTask: (String) -> Unit
) {
    val input = remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = { Text("Input") }
        )
        
        Spacer(modifier = Modifier.width(10.dp))
        
        IconButton(
            onClick = { onCreateTask(input.value) },
            content = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Create new task"
                )
            }
        )
    }
}