package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.material.Divider
import androidx.ui.material.IconButton
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.sudo.rizwan.composecalculator.AppState
import com.sudo.rizwan.composecalculator.R
import com.sudo.rizwan.composecalculator.performCalculation
import com.sudo.rizwan.composecalculator.saveCalculationToHistory

private val operationsColumn = listOf("Delete", "÷", "x", "-", "+")
private val numberColumns = listOf(
    listOf("7", "4", "1", "0"),
    listOf("8", "5", "2", "."),
    listOf("9", "6", "3", "=")
)

@Composable()
fun NumbersPanel(alpha: Int) {
    Stack {
        Row(modifier = Modifier.fillMaxSize()) {
            numberColumns.forEach { numberColumn ->
                Column(modifier = Modifier.weight(1f)) {
                    numberColumn.forEach { text ->
                        MainContentButton(text)
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
        DimOverlay(alpha = alpha)
    }
}

@Composable()
fun MainContentButton(text: String) {
    IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {
        if (text == "=") {
            performCalculation()
            saveCalculationToHistory()
        } else {
            if (AppState.inputText.text.length < 10) {
                AppState.inputText = TextFieldValue(text = AppState.inputText.text + text)
            }

            performCalculation()
        }
    }) {
        Box(gravity = ContentGravity.Center) {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = fontFamily(listOf(font(resId = R.font.jost_regular))),
                    fontSize = 29.sp
                )
            )
        }
    }
}

@Composable()
fun OperationItem(text: String) {
    if (text == "Delete") {
        IconButton(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onClick = {
                AppState.inputText = TextFieldValue(
                    text = AppState.inputText.text.substring(0, AppState.inputText.text.length - 1)
                )
                performCalculation()
            }) {
            Icon(
                asset = vectorResource(id = R.drawable.ic_outline_backspace_24),
                tint = AppState.theme.primary
            )
        }
    } else {
        IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {
            AppState.outputText = TextFieldValue(text = "")
            AppState.inputText = TextFieldValue(text = AppState.inputText.text + text)
        }) {
            Box(gravity = ContentGravity.Center) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = AppState.theme.primary,
                        fontSize = 22.sp
                    )
                )
            }
        }
    }
}
