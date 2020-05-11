package com.sudo.rizwan.composecalculator

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.IconButton
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.sp

@Composable()
fun TextItem(text: String) {
    IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {}) {
        Box(gravity = ContentGravity.Center) {
            Text(text = text, style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Medium))
        }
    }
}
