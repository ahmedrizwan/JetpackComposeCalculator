package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize

@Composable
fun DimOverlay(alpha: Int) {
    Box(modifier = Modifier.fillMaxSize(), backgroundColor = Color(50, 50, 50, alpha))
}
