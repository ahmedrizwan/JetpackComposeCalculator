package com.sudo.rizwan.composecalculator.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
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
import androidx.ui.material.Button
import androidx.ui.material.Card
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.sudo.rizwan.composecalculator.Drag
import com.sudo.rizwan.composecalculator.R

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
        shape = MaterialTheme.shapes.large,
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
            gravity = ContentGravity.BottomCenter
        ) {
            Column(
                modifier = Modifier.preferredHeight((boxHeight.value / 3.7).dp).fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth().preferredHeight(56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalGravity = ContentGravity.CenterVertically
                ) {
                    Button(onClick = {}, backgroundColor = Color.Transparent, elevation = 0.dp) {
                        Text(
                            text = "DEG", style = TextStyle(
                                color = Color(0xFF636363),
                                fontFamily = fontFamily(
                                    listOf(
                                        font(resId = R.font.jost_regular)
                                    )
                                ),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            asset = vectorResource(id = R.drawable.ic_more_vert_24),
                            tint = Color(0xFF636363)
                        )
                    }
                }

                Box(modifier = Modifier.preferredHeight(4.dp).preferredWidth(30.dp)) {
                    Icon(
                        asset = vectorResource(id = R.drawable.ic_rounded_dash),
                        tint = Color(0xFFD3D3D3)
                    )
                }
            }
        }
    }
}
