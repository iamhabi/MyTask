package task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetail(
    taskItem: TaskItem,
    isOpenDetail: MutableState<Boolean>,
    onDeleteItem: () -> Unit
) {
    fun editTask(title: String, description: String) {
        taskItem.title = title
        taskItem.description = description
        isOpenDetail.value = false
    }

    val datePickerState = rememberDatePickerState()
    val showDatePicker = remember { mutableStateOf(false) }

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

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { isOpenDetail.value = false },
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Close detail"
                    )
                }
            )

            Spacer(Modifier.weight(1F))

            IconButton(
                onClick = {
                    onDeleteItem()

                    isOpenDetail.value = false
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete task",
                        tint = Color.Red
                    )
                }
            )
        }

        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Title") },
            singleLine = true
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Due Date")

            Spacer(Modifier.weight(1F))

            TextButton(
                onClick = { showDatePicker.value = true },
                content = {
                    if (datePickerState.selectedDateMillis != null) {
                        Text("${datePickerState.selectedDateMillis}")
                    } else {
                        Text("Not setted")
                    }
                }
            )
        }

        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            label = { Text("Description") }
        )

        TextButton(
            onClick = {
                editTask(
                    title = title.value.text,
                    description = description.value.text
                )
            },
            modifier = Modifier.align(Alignment.End),
            content = { Text("OK") }
        )
    }

    if (showDatePicker.value) {
        AlertDialog(
            onDismissRequest = { showDatePicker.value = false },
            confirmButton = { showDatePicker.value = false },
            text = { DatePicker(datePickerState) }
        )
    }
}