package com.nickstamp.kit.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation(
    val none: Dp = 0.dp,
    val xsmall: Dp = 1.dp,
    val small: Dp = 2.dp,
    val default: Dp = 4.dp,
    val large: Dp = 8.dp,
    val xLarge: Dp = 12.dp
)

internal val LocalElevation = staticCompositionLocalOf { Elevation() }