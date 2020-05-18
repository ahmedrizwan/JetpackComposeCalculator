package com.sudo.rizwan.composecalculator

import androidx.ui.graphics.Color
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily

// TODO: Add theme switching

private val lightBackgroundColor = Color(0xFFf3f3f3)
private val darkBackgroundColor = Color(0xFF15202b)
private val primaryColor = Color(0xFF2376e6)

val grayColor = Color(0xFF636363)

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

// Fonts

val jostFontFamily = fontFamily(
    listOf(font(resId = R.font.jost_regular))
)
