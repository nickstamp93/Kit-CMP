package com.nickstamp.kit.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val none: Dp = 0.dp,
    val xsmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xlarge: Dp = 20.dp,
    val xxlarge: Dp = 24.dp,
    val xxxlarge: Dp = 32.dp,
    val xxxxlarge: Dp = 36.dp,
    val xxxxxlarge: Dp = 40.dp,
    val listBottomPadding: Dp = 160.dp,
    val list_padding_with_bottom_space: PaddingValues = PaddingValues(
        top = default,
        start = default,
        end = default,
        bottom = 160.dp
    )
)

internal val LocalSpacing = staticCompositionLocalOf { Dimens() }