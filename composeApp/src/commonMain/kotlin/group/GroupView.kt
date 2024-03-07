package group

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import TaskGroupList

@Composable
fun GroupView(
    taskGroups: SnapshotStateList<TaskGroup>,
    selectedIndex: MutableState<Int>
    
) {
    Row {
        Column(
            modifier = Modifier.weight(3.5F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1F)) {
                TaskGroupList(
                    taskGroups = taskGroups,
                    selectedIndex = selectedIndex
                )
            }
    
            AddGroup { title ->
                taskGroups.add(createNewTaskGroup(title))
            }
        }
    
        Box(
            modifier = Modifier.weight(0.5F),
            contentAlignment = Alignment.Center
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
        }
    }
}