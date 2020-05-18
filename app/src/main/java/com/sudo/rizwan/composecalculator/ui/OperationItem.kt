package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.IconButton
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.sp
import com.sudo.rizwan.composecalculator.AppState
import com.sudo.rizwan.composecalculator.AppState.inputText
import com.sudo.rizwan.composecalculator.AppState.outputText
import com.sudo.rizwan.composecalculator.R
import com.sudo.rizwan.composecalculator.performCalculation

@Composable()
fun OperationItem(text: String) {
    if (text == "Delete") {
        IconButton(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onClick = {
                inputText = TextFieldValue(
                    text = inputText.text.substring(0, inputText.text.length - 1)
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
            outputText = TextFieldValue(text = "")
            inputText = TextFieldValue(text = inputText.text + text)
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
