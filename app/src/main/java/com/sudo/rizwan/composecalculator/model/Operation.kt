package com.sudo.rizwan.composecalculator.model

import androidx.compose.Model

@Model
data class Operation(
    val input: String,
    val output: String,
    val date: String
)