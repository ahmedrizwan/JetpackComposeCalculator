package com.sudo.rizwan.composecalculator

import androidx.ui.graphics.Color
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val lightBackgroundColor = Color(0xFFf3f3f3)
private val darkBackgroundColor = Color(0xFF15202b)
private val primaryColor = Color(0xFF2376e6)

val lightThemeColors = lightColorPalette(
    primary = primaryColor,
    background = lightBackgroundColor,
    surface = lightBackgroundColor
)

val darkThemeColors = darkColorPalette(
    primary = primaryColor,
    background = darkBackgroundColor,
    surface = darkBackgroundColor
)
