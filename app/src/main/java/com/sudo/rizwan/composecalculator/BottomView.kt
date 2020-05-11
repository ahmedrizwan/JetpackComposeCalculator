package com.sudo.rizwan.composecalculator

import androidx.compose.Composable
import androidx.ui.animation.animatedFloat
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.animation.AnchorsFlingConfig
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.offset
import androidx.ui.layout.preferredHeight
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

@Composable()
fun BottomView(
    boxHeight: Dp,
    boxWidth: Dp
) {
    val min = 30.dp
    val max = boxWidth - 30.dp
    val (minPx, maxPx) = with(DensityAmbient.current) {
        min.toPx().value to max.toPx().value
    }
    val anchors = listOf(minPx, maxPx)
    val flingConfig = AnchorsFlingConfig(anchors)
    val position = animatedFloat(maxPx)
    position.setBounds(minPx, maxPx)

    Box(
        modifier = Modifier.fillMaxWidth().preferredHeight(boxHeight),
        backgroundColor = AppState.theme.background,
        gravity = ContentGravity.BottomStart
    ) {
        Numbers()
        val yOffset = with(DensityAmbient.current) { position.value.toDp() }
        Box(
            Modifier.offset(x = yOffset, y = 0.dp).fillMaxWidth()
                .draggable(
                    startDragImmediately = position.isRunning,
                    dragDirection = DragDirection.Horizontal,
                    onDragStopped = { position.fling(flingConfig, it) }
                ) { delta ->
                    position.snapTo(position.value + delta)
                    delta
                }
                .preferredHeight(boxHeight),
            backgroundColor = AppState.theme.primary
        )
    }
}
