package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextFieldValue
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.IconButton
import androidx.ui.text.TextStyle
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.unit.sp
import com.github.keelar.exprk.Expressions
import com.sudo.rizwan.composecalculator.AppState.inputText
import com.sudo.rizwan.composecalculator.AppState.outputText
import com.sudo.rizwan.composecalculator.R

@Composable()
fun TextItem(text: String) {
    IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {
        if (text == "=") {
            // calculate dummy result
            outputText = TextFieldValue(text = "21")
        } else {
            inputText = TextFieldValue(text = inputText.text + text)
            outputText = TextFieldValue(text = Expressions().eval(inputText.text).toString())
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
