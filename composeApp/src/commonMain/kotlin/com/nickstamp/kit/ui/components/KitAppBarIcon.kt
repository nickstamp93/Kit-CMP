package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun KitAppBarIcon(
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    icon: DrawableResource? = null,
    tint: Color = colors.onPrimary,
    contentDescription: String? = null
) {
    icon?.let {
        KitIconButton(
            icon = icon,
            onClickAction = onClickAction,
            tint = tint,
            contentDescription = contentDescription,
            modifier = modifier.padding(spacing.large)
        )
    }
}