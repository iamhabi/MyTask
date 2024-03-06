import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun EditTask(
    taskItem: TaskItem,
    isOpenEdit: MutableState<Boolean>
) {
    AlertDialog(
        title = { Text("Edit task") },
        text = {
            OutlinedTextField(
                value = taskItem.title.value,
                onValueChange = { taskItem.title.value = it }
            )
               },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = {
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