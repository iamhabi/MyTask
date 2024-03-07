package task

import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
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

    val focusRequester = remember { FocusRequester() }

    val originalTitle = taskItem.title
    
    val input = remember {
        mutableStateOf(
            TextFieldValue(
                text = taskItem.title,
                selection = TextRange(taskItem.title.length)
            )
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    AlertDialog(
        title = { Text("Edit task") },
        text = {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onKeyUp(
                        Key.Enter,
                        action = { editTask(input.value.text) }
                    ),
                label = { Text("$originalTitle ->") },
                singleLine = true
            )
        },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = { editTask(input.value.text) },
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