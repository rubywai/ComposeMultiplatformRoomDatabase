package com.rubylearner.roomdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubylearner.roomdb.data.database.RoomDb
import com.rubylearner.roomdb.data.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val db: RoomDb) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        // Load todos when the ViewModel is created
        getAllTodos()
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            db.todoDao().getAllTodos().collect { todos ->
                _todos.value = todos
            }
        }
    }

    fun addTodo(todo: TodoItem) {
        viewModelScope.launch {
            db.todoDao().insert(todo)
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            db.todoDao().delete(todo)
            getAllTodos() // Refresh the list after deleting a todo
        }
    }
}
