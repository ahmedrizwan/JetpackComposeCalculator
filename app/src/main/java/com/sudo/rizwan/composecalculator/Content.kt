package com.sudo.rizwan.composecalculator

import androidx.compose.Composable
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Constraints
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.animation.AnchorsFlingConfig
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.offset
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

@Composable
fun Content(
    constraints: Constraints,
    boxHeight: Dp,
    boxWidth: Dp
) {
    val start = -(constraints.maxHeight.value / 1.4f)
    val max = 0.dp
    val min = -(boxHeight / 1.4f)
    val (minPx, maxPx) = with(DensityAmbient.current) {
        min.toPx().value to max.toPx().value
    }
    val anchors = listOf(minPx, maxPx) // final position anchors
    val flingConfig = AnchorsFlingConfig(anchors)
    val position = animatedFloat(start) // dragging state
    position.setBounds(minPx, maxPx)

    Box(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        gravity = ContentGravity.BottomStart
    ) {
        BottomView(
            boxHeight = (boxHeight / 1.4f),
            boxWidth = boxWidth
        )
        val yOffset = with(DensityAmbient.current) { position.value.toDp() }
        Card(
            Modifier.offset(y = yOffset, x = 0.dp).fillMaxWidth()
                .draggable(
                    startDragImmediately = position.isRunning,
                    dragDirection = DragDirection.Vertical,
                    onDragStopped = { position.fling(flingConfig, it) }
                ) { delta ->
                    position.snapTo(position.value + delta)
                    delta // consume all delta no matter the bounds to avoid nested dragging (as example)
                }
                .preferredHeight(boxHeight),
            elevation = 4.dp,
            shape = MaterialTheme.shapes.large
        ) {}
    }
}
