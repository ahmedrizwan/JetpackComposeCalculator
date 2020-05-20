package com.sudo.rizwan.composecalculator

import androidx.compose.Model
import androidx.ui.foundation.TextFieldValue
import com.sudo.rizwan.composecalculator.model.Operation

@Model
object AppState {
    var theme = lightThemeColors
    var inputText = TextFieldValue(text = "")
    var outputText = TextFieldValue(text = "")
}

val operationsHistory = mutableListOf(
    Operation(input = "24+32", output = "56", date = "May 13, 2020"),
    Operation(input = "32+24", output = "56", date = "May 14, 2020"),
    Operation(input = "32+24-6", output = "50", date = "May 15, 2020"),
    Operation(input = "63+58", output = "121", date = " May 16, 2020")
)
