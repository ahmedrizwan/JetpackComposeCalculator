package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.material.IconButton
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.sudo.rizwan.composecalculator.AppState
import com.sudo.rizwan.composecalculator.Drag
import com.sudo.rizwan.composecalculator.R

@Composable()
fun SideView(
    boxHeight: Dp,
    drag: Drag
) {
    val position = drag.position
    val flingConfig = drag.flingConfig
    val yOffset = with(DensityAmbient.current) { position.value.toDp() }
    Box(
        Modifier.offset(x = yOffset, y = 0.dp)
            .fillMaxWidth()
            .draggable(
                startDragImmediately = position.isRunning,
                dragDirection = DragDirection.Horizontal,
                onDragStopped = { position.fling(flingConfig, it) }
            ) { delta ->
                position.snapTo(position.value + delta)
                delta
            }
            .preferredHeight(boxHeight),
        backgroundColor = AppState.theme.primary,
        gravity = ContentGravity.CenterStart
    ) {
        Box(modifier = Modifier.preferredWidth(30.dp), gravity = ContentGravity.Center) {
            if (position.value == position.max) {
                IconButton(onClick = {
                    position.animateTo(position.min)
                }) {
                    Icon(
                        asset = vectorResource(id = R.drawable.ic_keyboard_arrow_left_24),
                        tint = Color.White
                    )
                }
            } else {
                IconButton(onClick = {
                    position.animateTo(position.max)
                }) {
                    Icon(
                        asset = vectorResource(id = R.drawable.ic_keyboard_arrow_right_24),
                        tint = Color.White
                    )
                }
            }
        }
        Options()
    }
}

@Composable
private fun Options() {
    val optionColumns = listOf(
        listOf("INV", "sin", "ln", "π", "("),
        listOf("RAD", "cos", "log", "e", ")"),
        listOf("%", "tan", "√", "^", "!")
    )
    Row(modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 110.dp)) {
        // Three columns
        optionColumns.forEach { optionColumn ->
            Column(modifier = Modifier.weight(1f)) {
                optionColumn.forEach { text ->
                    SideViewTextItem(text = text)
                }
            }
        }
    }
}

@Composable()
private fun SideViewTextItem(text: String) {
    IconButton(modifier = Modifier.weight(1f).fillMaxWidth(), onClick = {}) {
        Box(gravity = ContentGravity.Center) {
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}
