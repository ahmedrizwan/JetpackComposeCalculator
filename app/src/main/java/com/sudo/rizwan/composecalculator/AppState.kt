package com.sudo.rizwan.composecalculator

import androidx.compose.Model
import androidx.ui.foundation.TextFieldValue

@Model
object AppState {
    var theme = lightThemeColors
    var inputText = TextFieldValue(text = "")
    var outputText = TextFieldValue(text = "")
    val operationsHistor = mutableListOf<Operation>()
}

fun isLightTheme(): Boolean {
    return AppState.theme.isLight
}

data class Operation(
    val text: String
)
