package com.rubylearner.roomdb

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rubylearner.roomdb.composable.ToDoList
import com.rubylearner.roomdb.viewmodel.TodoViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val viewModel: TodoViewModel = koinViewModel()
    val todoItems = viewModel.todos.collectAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }



    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("To Do App") }) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                        showDialog = true
                    },
                    content = { Icon(Icons.Filled.Add, contentDescription = "Add Todo") }
                )
            }
        ) {
            ToDoList(
                todoItems, viewModel,
                {
                    showDialog = false
                },
                showDialog,
            )
        }
    }
}