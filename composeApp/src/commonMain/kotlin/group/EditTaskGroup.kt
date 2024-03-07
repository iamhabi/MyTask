package group

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
fun EditTaskGroup(
    taskGroup: TaskGroup,
    isOpenEdit: MutableState<Boolean>
) {
    fun editGroup(title: String) {
        taskGroup.title = title
        isOpenEdit.value = false
    }
    
    val originalTitle = taskGroup.title
    
    val input = remember { mutableStateOf(taskGroup.title) }
    
    AlertDialog(
        title = { Text("Edit group") },
        text = {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                modifier = Modifier.onKeyUp(
                    Key.Enter,
                    action = { editGroup(input.value) }
                ),
                label = { Text("$originalTitle ->") },
                singleLine = true
            )
               },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = { editGroup(input.value) },
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