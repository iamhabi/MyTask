package task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskDetail(
    taskItem: TaskItem,
    isOpenDetail: MutableState<Boolean>
) {
    AlertDialog(
        title = { Text(taskItem.title) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(100.dp)
            ) {
                Text(text = taskItem.description)
            }
        },
        onDismissRequest = { isOpenDetail.value = false },
        confirmButton = {
            TextButton(
                onClick = { isOpenDetail.value = false },
                content = { Text("OK") }
            )
        }
    )
}