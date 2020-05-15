package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeight
import androidx.ui.unit.Dp
import com.sudo.rizwan.composecalculator.AppState

@Composable()
fun BottomView(
    boxHeight: Dp,
    boxWidth: Dp
) {
    Box(
        modifier = Modifier.fillMaxWidth().preferredHeight(boxHeight),
        backgroundColor = AppState.theme.background,
        gravity = ContentGravity.BottomStart
    ) {
        Numbers()
        SideView(
            boxHeight = boxHeight,
            boxWidth = boxWidth
        )
    }
}
