import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun EditTask(
    taskItem: TaskItem,
    isOpenEdit: MutableState<Boolean>
) {
    val originalTitle = taskItem.title

    val input = remember { mutableStateOf(taskItem.title) }

    AlertDialog(
        title = { Text("Edit task") },
        text = {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                label = { Text("$originalTitle ->") }
            )
               },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    taskItem.title = input.value
                    isOpenEdit.value = false
                          },
                content = { Text("OK") }
            )
                        },
        dismissButton = {
            TextButton(
                onClick = { isOpenEdit.value = false },
                content = { Text("Cancel") }
            )
        }
    )
}