package com.nickstamp.kit.presentation.shared.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme.shapes
import com.nickstamp.kit.presentation.theme.highEmphasis
import com.nickstamp.kit.presentation.theme.lowEmphasis

@Composable
fun KitVerticalProgressBar(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    animate: Boolean = false,
    width: Dp = 10.dp,
    shape: Shape = shapes.rounded50,
    color: Color = colorScheme.primary.highEmphasis(),
    backgroundColor: Color = colorScheme.primary.lowEmphasis()
) {

    val percent = remember(progress()) { Animatable(if (animate) 0f else progress()) }

    LaunchedEffect(Unit) {
        if (animate) {
            percent.animateTo(
                targetValue = progress(),
                animationSpec = tween(
                    durationMillis = 700,
                    delayMillis = 300
                )
            )
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .clip(shape)
            .drawBehind { drawRect(backgroundColor) }
    ) {
        if (progress() > 0f) {
            Spacer(
                modifier = Modifier
                    .clip(shape = shape)
                    .background(color = color)
                    .fillMaxHeight(percent.value)
                    .fillMaxWidth()
            )
        }

    }
}

