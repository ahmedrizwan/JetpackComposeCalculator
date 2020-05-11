package com.sudo.rizwan.composecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.animatedFloat
import androidx.ui.core.*
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
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = darkThemeColors) {
                WithConstraints { constraints, _ ->
                    val boxHeight = with(DensityAmbient.current) { constraints.maxHeight.toDp() }
                    val boxWidth = with(DensityAmbient.current) { constraints.maxWidth.toDp() }
                    DraggableWithFling(
                        constraints = constraints,
                        boxHeight = boxHeight,
                        boxWidth = boxWidth
                    )
                }
            }
        }
    }

    @Composable
    private fun DraggableWithFling(
        constraints: Constraints,
        boxHeight: Dp,
        boxWidth: Dp
    ) {
        val start = -(constraints.maxHeight.value / 1.5f)
        val max = 0.dp
        val min = -(boxHeight / 1.5f)
        val (minPx, maxPx) = with(DensityAmbient.current) {
            min.toPx().value to max.toPx().value
        }
        // define anchors (final position) and fling behavior to go to these anchors after drag
        val anchors = listOf(minPx, maxPx)
        val flingConfig = AnchorsFlingConfig(anchors)
        // define and set up animatedFloat as our dragging state
        val position = animatedFloat(start)
        position.setBounds(minPx, maxPx)

        // Seekbar itself
        Box(
            modifier = Modifier.fillMaxSize().draggable(
                startDragImmediately = position.isRunning,
                dragDirection = DragDirection.Vertical,
                // launch fling with velocity to animate to the closes anchor
                onDragStopped = { position.fling(flingConfig, it) }
            ) { delta ->
                position.snapTo(position.value + delta)
                delta // consume all delta no matter the bounds to avoid nested dragging (as example)
            },
            backgroundColor = Color.Transparent
        ) {
            Numbers(
                constraints = constraints,
                boxHeight = boxHeight,
                boxWidth = boxWidth
            )
            val yOffset = with(DensityAmbient.current) { position.value.toDp() }
            Box(
                Modifier.offset(y = yOffset, x = 0.dp).fillMaxWidth()
                    .preferredHeight(boxHeight),
                backgroundColor = Color.Red
            )
        }
    }
}

@Composable()
private fun Numbers(
    constraints: Constraints,
    boxHeight: Dp,
    boxWidth: Dp
) {
    val bottomContainerHeight = 400.dp
    val min = 50.dp
    val max = boxWidth - 50.dp
    val (minPx, maxPx) = with(DensityAmbient.current) {
        min.toPx().value to max.toPx().value
    }

    // define anchors (final position) and fling behavior to go to these anchors after drag
    val anchors = listOf(minPx, maxPx)
    val flingConfig = AnchorsFlingConfig(anchors)
    // define and set up animatedFloat as our dragging state
    val position = animatedFloat(0f)
    position.setBounds(minPx, maxPx)

    Box(
        modifier = Modifier.fillMaxSize().draggable(
            startDragImmediately = position.isRunning,
            dragDirection = DragDirection.Horizontal,
            onDragStopped = { position.fling(flingConfig, it) }
        ) { delta ->
            position.snapTo(position.value + delta)
            delta // consume all delta no matter the bounds to avoid nested dragging (as example)
        },
        backgroundColor = Color.Transparent,
        gravity = ContentGravity.BottomCenter
    ) {
        val yOffset = with(DensityAmbient.current) { position.value.toDp() }
        Box(
            Modifier.offset(x = yOffset, y = 0.dp).fillMaxWidth()
                .preferredHeight(boxHeight),
            backgroundColor = Color.Blue
        )
    }
}
