package com.sudo.rizwan.composecalculator.ui

import androidx.animation.AnimatedFloat
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.graphics.Color
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.sudo.rizwan.composecalculator.*
import com.sudo.rizwan.composecalculator.AppState.inputText
import com.sudo.rizwan.composecalculator.AppState.operationsHistory
import com.sudo.rizwan.composecalculator.AppState.outputText
import com.sudo.rizwan.composecalculator.R
import com.sudo.rizwan.composecalculator.model.Operation
import kotlin.math.abs

@Composable
fun TopView(
    boxHeight: Dp,
    drag: Drag
) {
    val position = drag.position
    val flingConfig = drag.flingConfig
    val yOffset = with(DensityAmbient.current) { position.value.toDp() }
    val scrollerPosition = ScrollerPosition()
    Card(
        Modifier.offset(y = yOffset, x = 0.dp).fillMaxWidth()
            .draggable(
                startDragImmediately = position.isRunning,
                dragDirection = DragDirection.Vertical,
                onDragStopped = { position.fling(flingConfig, it) }
            ) { delta ->
                position.snapTo(position.value + delta)
                // scroll the history list to bottom when dragging the top panel
                // 90dp history item height is an approximation
                scrollerPosition.smoothScrollBy(operationsHistory.size * 90.dp.value)
                // consume all delta no matter the bounds to avoid nested dragging (as example)
                delta
            }
            .preferredHeight(boxHeight),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.large,
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalGravity = ContentGravity.CenterHorizontally
        ) {
            ExpandedTopBar()
            History(scrollerPosition)
            Spacer(modifier = Modifier.preferredHeight(2.dp))
            CollapsedContent(boxHeight, position)
            RoundedDash()
        }
    }
}

@Composable
private fun ExpandedTopBar() {
    Surface(elevation = 2.dp, color = Color.White) {
        Row(
            modifier = Modifier.fillMaxWidth().preferredHeight(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = ContentGravity.CenterVertically
        ) {
            Row(verticalGravity = Alignment.CenterVertically) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        asset = Icons.Filled.ArrowBack,
                        tint = AppState.theme.primary
                    )
                }
                Spacer(modifier = Modifier.preferredWidth(16.dp))
                Text(
                    text = "History",
                    style = TextStyle(
                        color = AppState.theme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = jostFontFamily
                    )
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_more_vert_24),
                    tint = AppState.theme.primary
                )
            }
        }
    }
}

@Composable
private fun History(scrollerPosition: ScrollerPosition) {
    VerticalScroller(scrollerPosition = scrollerPosition, modifier = Modifier.weight(1f)) {
        Column {
            operationsHistory.forEachIndexed { index, item ->
                HistoryItem(item)
                if (index != operationsHistory.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun HistoryItem(item: Operation) {
    Column(
        modifier = Modifier.padding(top = 16.dp, bottom = 36.dp, start = 24.dp, end = 16.dp),
        horizontalGravity = Alignment.End
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.date, style = TextStyle(
                color = AppState.theme.primary, fontSize = 16.sp,
                fontFamily = jostFontFamily
            )
        )
        Text(
            text = item.input,
            style = TextStyle(
                color = Color(0xFF2C2C2C),
                fontSize = 36.sp,
                fontFamily = jostFontFamily
            )
        )
        Text(
            text = item.output,
            style = TextStyle(
                color = Color(0xFF636363),
                fontSize = 36.sp,
                fontFamily = jostFontFamily
            )
        )
    }
}

@Composable
private fun CollapsedContent(boxHeight: Dp, position: AnimatedFloat) {
    // Some scary maths
    val originalHeight = (boxHeight.value / 3.7)
    val div = abs(position.min / originalHeight)
    val adjustedHeight = originalHeight + ((position.min - position.value) / div)
    val height = if (adjustedHeight > 0f) {
        adjustedHeight.toFloat()
    } else {
        0f
    }
    val shouldDisplay = position.min - position.value == 0f
    Column(
        modifier = Modifier.preferredHeight(height.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        if (shouldDisplay) {
            Column {
                CollapsedTopBar()
                TextFields()
            }

            // No space between top bar & text fields
        }
    }
}

@Composable
fun WhiteOverlay(alpha: Int) {
    val finalAlpha = if (alpha > 255) {
        255
    } else {
        alpha
    }
    Box(modifier = Modifier.fillMaxSize(), backgroundColor = Color(255, 255, 255, finalAlpha))
}


@Composable
private fun TextFields() {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxWidth(),
        horizontalGravity = Alignment.End
    ) {
        TextField(
            value = inputText,
            onValueChange = { textFieldValue ->
                if (textFieldValue.text.length < 10) {
                    inputText = textFieldValue
                }
            },
            keyboardType = KeyboardType.Number,
            textStyle = TextStyle(fontSize = 46.sp, fontFamily = jostFontFamily)
        )
        Text(
            text = outputText.text,
            style = TextStyle(
                color = grayColor,
                fontSize = 36.sp,
                fontFamily = jostFontFamily
            ),
            overflow = TextOverflow.Ellipsis,
            softWrap = false,
            maxLines = 1
        )
    }
}

@Composable
private fun RoundedDash() {
    Box(
        modifier = Modifier.preferredHeight(24.dp).preferredWidth(30.dp)
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Icon(
            asset = vectorResource(id = R.drawable.ic_rounded_dash),
            tint = Color(0xFFD3D3D3)
        )
    }
}

@Composable
private fun CollapsedTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth().preferredHeight(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalGravity = ContentGravity.CenterVertically
    ) {
        Button(
            onClick = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Text(
                text = "DEG", style = TextStyle(
                    color = Color(0xFF636363),
                    fontFamily = jostFontFamily,
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
}
