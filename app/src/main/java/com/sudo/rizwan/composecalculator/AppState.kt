package com.sudo.rizwan.composecalculator

import androidx.compose.Model

@Model
object AppState {
    var theme = lightThemeColors
}

fun isLightTheme(): Boolean {
    return AppState.theme.isLight
}
