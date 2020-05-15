package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Constraints
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.animation.AnchorsFlingConfig
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.sudo.rizwan.composecalculator.Drag

@Composable
fun Content(
    constraints: Constraints,
    boxHeight: Dp,
    boxWidth: Dp
) {
    // Side drag
    val sideMin = 90.dp
    val sideMax = boxWidth - 30.dp
    val (sideMinPx, sideMaxPx) = with(DensityAmbient.current) {
        sideMin.toPx().value to sideMax.toPx().value
    }
    val sideAnchors = listOf(sideMinPx, sideMaxPx)
    val sideFlingConfig = AnchorsFlingConfig(sideAnchors)
    val sidePosition = animatedFloat(sideMaxPx)
    sidePosition.setBounds(sideMinPx, sideMaxPx)

    val sideDrag = Drag(
        position = sidePosition,
        flingConfig = sideFlingConfig
    )

    // Top drag
    val topStart = -(constraints.maxHeight.value / 1.4f)
    val topMax = 0.dp
    val topMin = -(boxHeight / 1.4f)
    val (topMinPx, topMaxPx) = with(DensityAmbient.current) {
        topMin.toPx().value to topMax.toPx().value
    }
    val anchors = listOf(topMinPx, topMaxPx) // final position anchors
    val topFlingConfig = AnchorsFlingConfig(anchors)
    val topPosition = animatedFloat(topStart) // for dragging state
    topPosition.setBounds(topMinPx, topMaxPx)

    val topDrag = Drag(
        position = topPosition,
        flingConfig = topFlingConfig
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        gravity = ContentGravity.BottomStart
    ) {
        BottomView(
            boxHeight = (boxHeight / 1.4f),
            sideDrag = sideDrag,
            topDrag = topDrag
        )
        TopView(
            boxHeight = boxHeight,
            drag = topDrag
        )
    }
}
