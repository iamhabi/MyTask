package task

import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import onKeyUp

@Composable
fun EditTask(
    taskItem: TaskItem,
    isOpenEdit: MutableState<Boolean>
) {
    fun editTask(title: String) {
        taskItem.title = title
        isOpenEdit.value = false
    }

    val originalTitle = taskItem.title

    val input = remember { mutableStateOf(taskItem.title) }

    AlertDialog(
        title = { Text("Edit task") },
        text = {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                modifier = Modifier.onKeyUp(
                    Key.Enter,
                    action = { editTask(input.value) }
                ),
                label = { Text("$originalTitle ->") },
                singleLine = true
            )
               },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = { editTask(input.value) },
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