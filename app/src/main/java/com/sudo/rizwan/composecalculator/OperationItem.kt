package com.sudo.rizwan.composecalculator

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.IconButton
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.sp

@Composable()
fun OperationItem(text: String) {
    if (text == "Delete") {
        IconButton(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onClick = {}) {
            Icon(
                asset = vectorResource(id = R.drawable.ic_outline_backspace_24),
                tint = AppState.theme.primary
            )
        }
    } else {
        IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {}) {
            Box(gravity = ContentGravity.Center) {
                Text(
                    text = text,
                    style = TextStyle(color = AppState.theme.primary, fontSize = 24.sp)
                )
            }
        }
    }
}
