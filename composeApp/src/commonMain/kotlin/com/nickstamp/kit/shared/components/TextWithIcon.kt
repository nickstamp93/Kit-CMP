package com.nickstamp.kit.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class IconGravity {
    START, END
}

@Composable
fun KitTextWithIcon(
    text: String,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    maxLines: Int = 2,
    tint: Color = colorScheme.onSurface,
    style: TextStyle = AppTheme.typography.regular14,
    iconGravity: IconGravity = IconGravity.START,
    iconSize: Dp = 24.dp,
    iconSpacing: Dp = AppTheme.spacing.medium
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(iconSpacing),
        modifier = modifier
    ) {
        if (iconGravity == IconGravity.START) {
            Icon(
                painter = painterResource(icon),
                tint = tint,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }

        Text(
            text = text,
            maxLines = maxLines,
            color = tint,
            style = style,
            overflow = TextOverflow.Ellipsis
        )

        if (iconGravity == IconGravity.END) {
            Icon(
                painter = painterResource(icon),
                tint = tint,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }
    }

}