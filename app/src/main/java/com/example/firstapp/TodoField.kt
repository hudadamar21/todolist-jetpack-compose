package com.example.firstapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoField(
    todo: String,
    isUpdated: Boolean,
    handleValueChange: (text: String) -> Unit,
    onAddOrUpdate: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = todo,
            onValueChange = handleValueChange,
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(text = "your todo..")
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onAddOrUpdate,
            contentPadding = PaddingValues(18.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = if (isUpdated) "Update" else "Add")
        }
    }
}