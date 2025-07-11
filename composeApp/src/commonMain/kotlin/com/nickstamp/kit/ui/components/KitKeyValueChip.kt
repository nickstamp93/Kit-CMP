package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography

object KitKeyValueChipDefaults {

    val textStyle: TextStyle
        @Composable get() = typography.regular12

    val keyValueChipColors: KitKeyValueChipColors
        @Composable get() = KitKeyValueChipColors(
            captionColor = colors.onPrimary,
            captionContainerColor = colors.primary,
            valueColor = colors.onSecondary,
            valueContainerColor = colors.secondary
        )
}

@Immutable
data class KitKeyValueChipColors(
    val captionColor: Color,
    val captionContainerColor: Color,
    val valueColor: Color,
    val valueContainerColor: Color,
)

@Composable
fun KitKeyValueChip(
    caption: String,
    value: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = KitKeyValueChipDefaults.textStyle,
    colors: KitKeyValueChipColors = KitKeyValueChipDefaults.keyValueChipColors,
) {
    Row(
        modifier = modifier.clip(shapes.rounded25)
    ) {
        Text(
            text = caption,
            style = textStyle.copy(color = colors.captionColor),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .drawBehind { drawRect(colors.captionContainerColor) }
                .padding(spacing.small)
        )
        Text(
            text = value,
            style = textStyle.copy(
                fontWeight = FontWeight.Bold,
                color = colors.valueColor
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .drawBehind { drawRect(colors.valueContainerColor) }
                .padding(
                    vertical = spacing.small,
                    horizontal = spacing.default
                )
        )
    }
}

@Composable
fun KitKeyValueIconChip(
    painter: Painter,
    value: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = KitKeyValueChipDefaults.textStyle,
    colors: KitKeyValueChipColors = KitKeyValueChipDefaults.keyValueChipColors,
) {
    Row(
        modifier = modifier.clip(shapes.small)
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = colors.captionColor,
            modifier = Modifier
                .size(23.dp)
                .drawBehind { drawRect(colors.captionContainerColor) }
                .padding(spacing.small)
        )
        Text(
            text = value,
            style = textStyle.copy(
                fontWeight = FontWeight.Bold,
                color = colors.valueColor
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .drawBehind { drawRect(colors.valueContainerColor) }
                .padding(
                    vertical = spacing.small,
                    horizontal = spacing.default
                )
        )
    }
}