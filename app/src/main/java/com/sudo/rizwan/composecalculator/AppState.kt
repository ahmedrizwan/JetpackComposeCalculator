package com.sudo.rizwan.composecalculator

import androidx.compose.Model
import androidx.ui.foundation.TextFieldValue
import com.sudo.rizwan.composecalculator.AppState.outputText
import com.sudo.rizwan.composecalculator.exprk.Expressions
import com.sudo.rizwan.composecalculator.model.Operation
import java.math.RoundingMode

@Model
object AppState {
    var theme = lightThemeColors
    var inputText = TextFieldValue(text = "")
    var outputText = TextFieldValue(text = "")
    val operationsHistory = mutableListOf(
        Operation(input = "24+32", output = "56", date = "May 13, 2020"),
        Operation(input = "32+24", output = "56", date = "May 14, 2020"),
        Operation(input = "32+24-6", output = "50", date = "May 15, 2020"),
        Operation(input = "63+58", output = "121", date = " May 16, 2020")
    )
}

// Utils

fun isLightTheme(): Boolean {
    return AppState.theme.isLight
}

fun performCalculation() {
    val finalExpression = AppState.inputText.text.replace('x', '*').replace('÷', '/')
    val expressions = arrayOf('+', '-', '*', '/')
    if (!expressions.any { finalExpression.contains(it) }) {
        return
    }
    if (expressions.any { finalExpression.endsWith(it) }) {
        outputText = TextFieldValue(text = "")
        return
    }
    val eval = Expressions().eval(finalExpression)
    outputText = if (eval.toString().contains(".")) {
        val rounded = eval.setScale(1, RoundingMode.UP)
        TextFieldValue(text = rounded.toString())
    } else {
        TextFieldValue(text = eval.toString())
    }
}
