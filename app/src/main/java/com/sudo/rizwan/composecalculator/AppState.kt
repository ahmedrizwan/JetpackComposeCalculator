package com.sudo.rizwan.composecalculator

import androidx.compose.Model
import androidx.ui.foundation.TextFieldValue
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily

@Model
object AppState {
    var theme = lightThemeColors
    var inputText = TextFieldValue(text = "")
    var outputText = TextFieldValue(text = "")
    val operationsHistory = mutableListOf<Operation>(
        Operation(input = "24+32", output = "56", date = "May 13, 2020"),
        Operation(input = "32+24", output = "56", date = "May 14, 2020"),
        Operation(input = "32+24-6", output = "50", date = "May 15, 2020"),
        Operation(input = "63+58", output = "121", date = " May 16, 2020")
    )
}

fun isLightTheme(): Boolean {
    return AppState.theme.isLight
}

data class Operation(
    val input: String,
    val output: String,
    val date: String
)

val jostFontFamily = fontFamily(
    listOf(
        font(resId = R.font.jost_regular)
    )
)
