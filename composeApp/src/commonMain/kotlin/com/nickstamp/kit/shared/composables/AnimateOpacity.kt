package com.nickstamp.kit.shared.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.nickstamp.kit.presentation.theme.AppTheme.opacity

@Composable
fun animatedOpacity(
): State<Float> {
    val infiniteTransition = rememberInfiniteTransition(label = "AlphaAnim")

    return infiniteTransition.animateFloat(
        initialValue = opacity.emphasisLow,
        targetValue = opacity.emphasisHigh,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ColorAnimation"
    )
}