package com.example.firstapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoList() {
    var todo by remember {
        mutableStateOf("")
    }
    var todos by remember {
        mutableStateOf(
            mutableListOf<Todo>(
                Todo(1, "Todo 1", false),
                Todo(2, "Todo 2", true),
                Todo(3, "Todo 3", false),
                Todo(4, "Todo 4", true),
            )
        )
    }
    var updatedId by remember {
        mutableStateOf<Int>(0)
    }
    var isUpdated by remember {
        mutableStateOf(false)
    }

    fun updateTodoById(id: Int, newName: String?, newIsComplete: Boolean?) {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            todos = todos.toMutableList().also {
                it[index] = Todo(
                    index,
                    newName ?: it[index].title,
                    newIsComplete ?: it[index].isCompleted
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TodoField(
            todo = todo,
            isUpdated = isUpdated,
            handleValueChange = { text ->
                todo = text
            },
            onAddOrUpdate = {
                if (isUpdated) {
                    val updatedTodoIndex = todos.indexOfFirst { it.id == updatedId }
                    updateTodoById(
                        updatedId,
                        todo,
                        todos[updatedTodoIndex].isCompleted
                    )
                } else if (todo.isNotBlank()) {
                    todos = (todos + Todo(
                        todos.size + 1,
                        todo,
                        false
                    )) as MutableList<Todo>
                }
                isUpdated = false
                todo = ""
            }
        )

        LazyColumn() {
            items(todos.filter { !it.isCompleted }) { currentTodo ->
                TodoItem(
                    todo = currentTodo,
                    onEditClick = {
                        isUpdated = true
                        updatedId = currentTodo.id
                        todo = currentTodo.title
                    },
                    onDeleteClick = {
                        todos =
                            todos.filterNot { it == currentTodo } as MutableList<Todo>
                    },
                    onCompletedClick = {
                        updateTodoById(currentTodo.id, currentTodo.title, !currentTodo.isCompleted)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Completed Todo:")
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn() {
            items(todos.filter { it.isCompleted }) { currentTodo ->
                TodoItem(
                    todo = currentTodo,
                    onEditClick = {
                        isUpdated = true
                        updatedId = currentTodo.id
                        todo = currentTodo.title
                    },
                    onDeleteClick = {
                        todos =
                            todos.filterNot { it == currentTodo } as MutableList<Todo>
                    },
                    onCompletedClick = {
                        updateTodoById(currentTodo.id, currentTodo.title, !currentTodo.isCompleted)
                    }
                )
            }
        }
    }
}