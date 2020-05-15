package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.unit.dp

@Composable()
fun Numbers() {
    val operationsColumn = listOf("Delete", "÷", "x", "-", "+")
    val numberColumns = listOf(
        listOf("7", "4", "1", "0"),
        listOf("8", "5", "2", "."),
        listOf("9", "6", "3", "=")
    )

    Row(modifier = Modifier.fillMaxSize()) {
        numberColumns.forEach { numberColumn ->
            Column(modifier = Modifier.weight(1f)) {
                numberColumn.forEach { text ->
                    TextItem(text)
                }
            }
        }
        Divider(
            modifier = Modifier.preferredWidth(1.dp).fillMaxHeight(),
            color = Color(0xFFd3d3d3)
        )
        Column(modifier = Modifier.weight(1.3f)) {
            operationsColumn.forEach { operation ->
                OperationItem(text = operation)
            }
        }
        Spacer(modifier = Modifier.preferredWidth(30.dp))
    }
}
