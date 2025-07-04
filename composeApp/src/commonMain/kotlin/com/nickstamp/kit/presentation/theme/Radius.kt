package com.nickstamp.kit.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Radius(
    val none: Dp = 0.dp,
    val xxsmall: Dp = 2.dp,
    val xsmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val large: Dp = 16.dp,
    val xlarge: Dp = 24.dp,
)

internal val LocalRadius = staticCompositionLocalOf { Radius() }