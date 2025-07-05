package com.nickstamp.kit.shared.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun KitPulsatingCircle(
    modifier: Modifier = Modifier,
    minScale: Float = 1.5f,
    maxScale: Float = 2f,
    pulseDuration: Int = 500,
    color: Color = colorScheme.tertiary.copy(alpha = 0.3f),
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        val infiniteTransition = rememberInfiniteTransition(
            label = "pulse"
        )

        val scale by infiniteTransition.animateFloat(
            initialValue = minScale,
            targetValue = maxScale,
            label = "scaleAnim",
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = pulseDuration,
                    easing = LinearOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = color,
                center = Offset(
                    x = canvasWidth / 2,
                    y = canvasHeight / 2
                ),
                radius = size.minDimension / 4f,
            )
        }
    }
}