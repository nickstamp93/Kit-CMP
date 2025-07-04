package com.nickstamp.kit.presentation.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shapes(
    val rectangle: Shape = RectangleShape,
    val small: Shape = RoundedCornerShape(4.dp),
    val default: Shape = RoundedCornerShape(8.dp),
    val large: Shape = RoundedCornerShape(16.dp),
    val xlarge: Shape = RoundedCornerShape(24.dp),
    val rounded25: Shape = RoundedCornerShape(25),
    val rounded50: Shape = RoundedCornerShape(50),
    val rounded75: Shape = RoundedCornerShape(75),
    val circle:Shape = CircleShape,
    val bottomRoundedDefault: Shape = RoundedCornerShape(
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    ),
    val bottomRoundedDMedium: Shape = RoundedCornerShape(
        bottomStart = 12.dp,
        bottomEnd = 12.dp
    ),
    val bottomRoundedLarge: Shape = RoundedCornerShape(
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    ),
    val bottomRoundedExtraLarge: Shape = RoundedCornerShape(
        bottomStart = 24.dp,
        bottomEnd = 24.dp
    ),
    val topRoundedDefault: Shape = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp
    ),
    val topRoundedMedium: Shape = RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 12.dp
    ),
    val topRoundedLarge: Shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp
    ),
    val topRoundedExtraLarge: Shape = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp
    ),
    val endRoundedDefault: Shape = RoundedCornerShape(
        topEnd = 8.dp,
        bottomEnd = 8.dp
    ),
    val endRoundedMedium: Shape = RoundedCornerShape(
        topEnd = 12.dp,
        bottomEnd = 12.dp
    ),
    val endRoundedLarge: Shape = RoundedCornerShape(
        topEnd = 16.dp,
        bottomEnd = 16.dp
    ),
    val endRoundedExtraLarge: Shape = RoundedCornerShape(
        topEnd = 24.dp,
        bottomEnd = 24.dp
    ),
    val startRoundedDefault: Shape = RoundedCornerShape(
        topStart = 8.dp,
        bottomStart = 8.dp
    ),
    val startRoundedMedium: Shape = RoundedCornerShape(
        topStart = 12.dp,
        bottomStart = 12.dp
    ),
    val startRoundedLarge: Shape = RoundedCornerShape(
        topStart = 16.dp,
        bottomStart = 16.dp
    ),
    val startRoundedExtraLarge: Shape = RoundedCornerShape(
        topStart = 24.dp,
        bottomStart = 24.dp
    ),
)

internal val LocalShapes = staticCompositionLocalOf { Shapes() }