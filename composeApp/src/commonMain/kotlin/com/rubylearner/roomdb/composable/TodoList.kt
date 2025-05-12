package com.rubylearner.roomdb.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rubylearner.roomdb.data.model.TodoItem
import com.rubylearner.roomdb.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

@Composable
fun ToDoList(
    todoItems: State<List<TodoItem>>,
    viewModel: TodoViewModel,
    onDismiss: () -> Unit,
    showDialog: Boolean
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            items(todoItems.value) { todo ->
                TodoItemView(todo, onDelete = {
                    viewModel.deleteTodo(todo)
                })
            }
        }

        if (showDialog) {
            TodoAddDialog(
                onDismiss = onDismiss,
                onSave = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        // Insert new Todo item
                        viewModel.addTodo(TodoItem(0, title, description))
                        title = ""
                        description = ""
                    }
                    onDismiss()


                },
                title = title,
                description = description,
                onTitleChange = { title = it },
                onDescriptionChange = { description = it }
            )
        }
    }
}

@Composable
fun TodoItemView(todo: TodoItem, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("To do is")
            Text(text = todo.title)
            Text(text = todo.description)
        }
        IconButton(onClick = { onDelete() }) {  // Directly call the function here
            Icon(Icons.Filled.Delete, contentDescription = "Delete Todo")
        }
    }
}

@Composable
fun TodoAddDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Todo") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                enabled = title.isNotBlank() && description.isNotBlank()
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
