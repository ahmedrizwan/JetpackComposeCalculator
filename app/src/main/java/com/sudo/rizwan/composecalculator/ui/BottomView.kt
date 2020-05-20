package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeight
import androidx.ui.unit.Dp
import com.sudo.rizwan.composecalculator.AppState
import com.sudo.rizwan.composecalculator.model.Drag

@Composable()
fun BottomView(
    boxHeight: Dp,
    sideDrag: Drag,
    topDrag: Drag
) {
    // Some maths to calculate alphas corresponding to the drag
    val sidePosition = sideDrag.position
    val sideDivisor = sidePosition.max / 255
    val alphaForSideDrag = (255 - sidePosition.value / sideDivisor).toInt()

    val topPosition = topDrag.position
    val topDivisor = topPosition.min / 255
    val alphaForTopDrag = (255 - topPosition.value / topDivisor).toInt()

    Box(
        modifier = Modifier.fillMaxWidth().preferredHeight(boxHeight),
        backgroundColor = AppState.theme.background,
        gravity = ContentGravity.BottomStart
    ) {
        NumbersPanel(alpha = alphaForSideDrag)
        SideView(
            boxHeight = boxHeight,
            drag = sideDrag
        )
        // Overlay's alpha that corresponds to top view drag
        DimOverlay(alpha = alphaForTopDrag)
    }
}
