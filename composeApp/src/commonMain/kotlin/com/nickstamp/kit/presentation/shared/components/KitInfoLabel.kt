package com.nickstamp.kit.presentation.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.nickstamp.kit.presentation.theme.AppTheme

@Composable
fun KitInfoLabel(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shape: Shape = AppTheme.shapes.default,
    padding: Dp = AppTheme.spacing.small,
) {
    Text(
        text = text,
        style = AppTheme.typography.bold10.copy(color = textColor),
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .padding(padding)
    )
}