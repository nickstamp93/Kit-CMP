package com.nickstamp.kit.feature.intro.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme.radius
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.highEmphasis
import com.nickstamp.kit.ui.theme.lowEmphasis

private val DOT_SIZE = 10.dp
private val DOT_SIZE_ACTIVE = 16.dp

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(
            spacing.small,
            Alignment.CenterHorizontally
        ),
    ) {
        repeat(pageCount) { iteration ->
            DotIndicator(
                active = (iteration == currentPage),
                baseColor = Color.White
            )
        }
    }
}


@Composable
private fun DotIndicator(
    active: Boolean,
    baseColor: Color
) {
    val color = animateColorAsState(
        targetValue = if (active) {
            baseColor.highEmphasis()
        } else {
            baseColor.lowEmphasis()
        },
        label = "dotColorAnim"
    )

    val width = animateDpAsState(
        targetValue = if (active) {
            DOT_SIZE_ACTIVE
        } else {
            DOT_SIZE
        },
        label = "dotSizeAnim"
    )

    val cornerRadius = radius.small
    Box(
        modifier = Modifier
            .padding(2.dp)
            .drawBehind {
                drawRoundRect(
                    color.value,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
            .size(width = width.value, height = DOT_SIZE)
    )
}