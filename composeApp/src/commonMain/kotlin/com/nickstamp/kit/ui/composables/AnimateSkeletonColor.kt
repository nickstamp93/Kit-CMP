package com.nickstamp.kit.shared.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.lowestEmphasis
import com.nickstamp.kit.ui.theme.mediumEmphasis

@Composable
fun animatedSkeletonColor(
    initialColor: Color = colors.onSurface.mediumEmphasis(),
    targetColor: Color = colors.onSurface.lowestEmphasis()
): State<Color> {
    val infiniteTransition = rememberInfiniteTransition(label = "Shimmer")

    return infiniteTransition.animateColor(
        initialValue = initialColor,
        targetValue = targetColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ColorAnimation"
    )
}