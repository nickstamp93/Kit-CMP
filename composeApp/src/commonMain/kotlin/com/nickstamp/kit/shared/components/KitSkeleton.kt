package com.nickstamp.kit.shared.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.nickstamp.kit.presentation.theme.AppTheme

@Composable
fun KitSkeletonItem(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    shape: Shape = AppTheme.shapes.default,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Spacer(
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier)
            .then(if (height != null) Modifier.height(height) else Modifier)
            .clip(shape)
            .drawBehind {
                if (shape == CircleShape) {
                    drawCircle(color)
                } else {
                    drawRoundRect(color)
                }
            }
    )
}