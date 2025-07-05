package com.nickstamp.kit.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.nickstamp.kit.presentation.theme.AppTheme.elevation
import com.nickstamp.kit.presentation.theme.AppTheme.shapes

@Composable
fun PaletteColoredCard(
    color: () -> Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shadowElevation: Dp = elevation.default,
    shape: Shape = shapes.large,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        onClick = onClick,
        shadowElevation = shadowElevation,
        shape = shape,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .drawBehind {
                    drawRect(color = color())
                }
        ) {
            content()
        }
    }
}

@Composable
fun PaletteColoredBox(
    color: () -> Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = shapes.large,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = colorScheme.onSurface)
            )
    ) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .drawBehind {
                    drawRect(color = color())
                }
        ) {
            content()
        }
    }
}

@Composable
fun PaletteColoredContainer(
    color: () -> Color,
    modifier: Modifier = Modifier,
    shape: Shape = shapes.large,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(shape)
            .drawBehind {
                drawRect(color = color())
            }
    ) {
        content()
    }
}