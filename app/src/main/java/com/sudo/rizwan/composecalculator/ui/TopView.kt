package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.offset
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.sudo.rizwan.composecalculator.Drag

@Composable()
fun TopView(
    boxHeight: Dp,
    drag: Drag
) {
    val position = drag.position
    val flingConfig = drag.flingConfig
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
