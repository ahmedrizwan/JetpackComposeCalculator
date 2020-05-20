package com.sudo.rizwan.composecalculator

import androidx.ui.foundation.TextFieldValue
import com.sudo.rizwan.composecalculator.exprk.Expressions
import com.sudo.rizwan.composecalculator.model.Operation
import java.math.RoundingMode
import java.util.*

fun performCalculation() {
    val finalExpression = AppState.inputText.text.replace('x', '*').replace('÷', '/')
    val expressions = arrayOf('+', '-', '*', '/')
    if (!expressions.any { finalExpression.contains(it) }) {
        return
    }
    if (expressions.any { finalExpression.endsWith(it) }) {
        AppState.outputText = TextFieldValue(text = "")
        return
    }
    val eval = Expressions().eval(finalExpression)
    AppState.outputText = if (eval.toString().contains(".")) {
        val rounded = eval.setScale(1, RoundingMode.UP)
        TextFieldValue(text = rounded.toString())
    } else {
        TextFieldValue(text = eval.toString())
    }
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun saveCalculationToHistory() {
    if (AppState.outputText.text.isNotEmpty()) {
        val calendar = Calendar.getInstance()
        val month: String = calendar.getDisplayName(
            Calendar.MONTH,
            Calendar.SHORT,
            Locale.getDefault()
        )
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        operationsHistory.add(
            Operation(
                input = AppState.inputText.text,
                output = AppState.outputText.text,
                date = "$month $day, $year"
            )
        )
    }
}

fun isLightTheme(): Boolean {
    return AppState.theme.isLight
}
