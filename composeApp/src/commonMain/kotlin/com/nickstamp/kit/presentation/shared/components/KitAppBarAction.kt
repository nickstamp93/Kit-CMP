package com.nickstamp.kit.presentation.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.presentation.theme.AppTheme.opacity
import com.nickstamp.kit.presentation.theme.AppTheme.spacing
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitAppBarIcon(
    iconRes: DrawableResource,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = colorScheme.onPrimary
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = null,
        tint = tint,
        modifier = modifier
            .alpha(opacity.from(enabled))
            .padding(spacing.large)
            .clickable(
                enabled = enabled,
                onClick = onClickAction,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, color = tint),
            )
    )
}
