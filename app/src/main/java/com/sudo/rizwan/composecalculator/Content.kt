package com.sudo.rizwan.composecalculator

import androidx.compose.Composable
import androidx.ui.core.Constraints
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.unit.Dp

@Composable
fun Content(
    constraints: Constraints,
    boxHeight: Dp,
    boxWidth: Dp
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        gravity = ContentGravity.BottomStart
    ) {
        BottomView(
            boxHeight = (boxHeight / 1.4f),
            boxWidth = boxWidth
        )
        TopView(constraints = constraints, boxHeight = boxHeight)
    }
}
