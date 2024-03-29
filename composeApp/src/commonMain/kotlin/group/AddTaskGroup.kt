package group

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import onKeyUp

@Composable
fun AddTaskGroup(
    onCreateGroup: (String) -> Unit
) {
    val input = remember { mutableStateOf("") }

    fun createGroup(title: String) {
        if (title == "") {
            return
        }

        onCreateGroup(title)

        input.value = ""
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            modifier = Modifier
                .weight(3F)
                .onKeyUp(Key.Enter, action = { createGroup(input.value) }),
            label = { Text("New Group") },
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))
        
        IconButton(
            enabled = input.value != "",
            modifier = Modifier.weight(1F),
            onClick = { createGroup(input.value) },
            content = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Create new task"
                )
            }
        )
    }
}
