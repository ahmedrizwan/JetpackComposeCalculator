package com.sudo.rizwan.composecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.DensityAmbient
import androidx.ui.core.WithConstraints
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = lightThemeColors) {
                WithConstraints { constraints, _ ->
                    val boxHeight = with(DensityAmbient.current) { constraints.maxHeight.toDp() }
                    val boxWidth = with(DensityAmbient.current) { constraints.maxWidth.toDp() }
                    Content(
                        constraints = constraints,
                        boxHeight = boxHeight,
                        boxWidth = boxWidth
                    )
                }
            }
        }
    }
}
