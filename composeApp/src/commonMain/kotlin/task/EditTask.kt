package task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EditTask(
    taskItem: TaskItem,
    isOpenEdit: MutableState<Boolean>
) {
    fun editTask(title: String, description: String) {
        taskItem.title = title
        taskItem.description = description
        isOpenEdit.value = false
    }

    val focusRequester = remember { FocusRequester() }

    val originalTitle = taskItem.title
    
    val title = remember {
        mutableStateOf(
            TextFieldValue(
                text = taskItem.title,
                selection = TextRange(taskItem.title.length)
            )
        )
    }

    val description = remember {
        mutableStateOf(
            TextFieldValue(
                text = taskItem.description,
                selection = TextRange(taskItem.description.length)
            )
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    AlertDialog(
        title = { Text("Edit task") },
        text = {
            Column(
                modifier = Modifier.padding(100.dp)
            ) {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    label = { Text("$originalTitle ->") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    modifier = Modifier.fillMaxHeight(),
                    label = { Text("Description") }
                )
            }
        },
        onDismissRequest = { isOpenEdit.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    editTask(
                        title = title.value.text,
                        description = description.value.text
                    )
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