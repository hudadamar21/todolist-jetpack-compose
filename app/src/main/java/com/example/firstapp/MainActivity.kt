package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.firstapp.ui.theme.FirstappTheme

data class Todo(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstappTheme {
                var todo by remember {
                    mutableStateOf("")
                }
                var todos by remember {
                    mutableStateOf(mutableListOf<Todo>(
                        Todo(1,"Todo 1", false),
                        Todo(2,"Todo 2", true),
                        Todo(3,"Todo 3", false),
                        Todo(4,"Todo 4", true),
                    ))
                }
                var updatedId by remember {
                    mutableStateOf<Int>(0)
                }
                var isUpdated by remember {
                    mutableStateOf(false)
                }

                fun updateTodoById(id: Int, newName: String?, newIsComplete: Boolean? ) {
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
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = todo,
                            onValueChange = { text ->
                                todo = text
                            },
                            modifier = Modifier.weight(1f),
                            placeholder = {
                                Text(text = "your todo..")
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                if (isUpdated) {
                                    val updatedTodoIndex = todos.indexOfFirst { it.id == updatedId }
                                    updateTodoById(updatedId, todo, todos[updatedTodoIndex].isCompleted)
                                } else if (todo.isNotBlank()) {
                                    todos = (todos + Todo(todos.size + 1, todo, false)) as MutableList<Todo>
                                }
                                isUpdated = false
                                todo = ""
                            },
                            contentPadding = PaddingValues(18.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = if (isUpdated) "Update" else "Add")
                        }
                    }

                    LazyColumn() {
                        itemsIndexed(todos.filter { !it.isCompleted }) { index, currentTodo ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = currentTodo.title,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(16.dp),
                                )
                                Row() {
                                    IconButton(
                                        onClick = {
                                            isUpdated = true
                                            updatedId = currentTodo.id
                                            todo = currentTodo.title

                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Edit",
                                                tint = Color.Gray
                                            )
                                        }
                                    )
                                    IconButton(
                                        onClick = {
                                            todos =
                                                todos.filterNot { it == currentTodo } as MutableList<Todo>
                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint = Color.Gray
                                            )
                                        }
                                    )
                                    IconButton(
                                        onClick = {
                                            updateTodoById(currentTodo.id, currentTodo.title, true)

                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = "Delete",
                                                tint = Color.Gray
                                            )
                                        }
                                    )
                                }
                            }
                            Divider()
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text= "Completed Todo:")
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn() {
                        itemsIndexed(todos.filter { it.isCompleted }) { index, currentTodo ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = currentTodo.title,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(16.dp),
                                )
                                Row() {
                                    IconButton(
                                        onClick = {
                                            isUpdated = true
                                            updatedId = index
                                            todo = currentTodo.title

                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Edit",
                                                tint = Color.Gray
                                            )
                                        }
                                    )
                                    IconButton(
                                        onClick = {
                                            todos =
                                                todos.filterNot { it == currentTodo } as MutableList<Todo>
                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint = Color.Gray
                                            )
                                        }
                                    )
                                    IconButton(
                                        onClick = {
                                            updateTodoById(currentTodo.id, currentTodo.title, false)
                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "Delete",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    )
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstappTheme {

    }
}

//@Composable
//fun Greeting(name: String) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(10) { i ->
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = null,
//                modifier = Modifier.size(100.dp)
//            )
//        }
//    }
//}
