package com.nickstamp.kit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.ui.theme.AppTheme.colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitIconButton(
    icon: DrawableResource,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = colors.onSurface,
    contentDescription: String? = null
) {
    val iconModifier = modifier.clickable(
        onClick = onClickAction,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(bounded = false, color = tint),
    )
    Icon(
        painter = painterResource(icon),
        tint = tint,
        contentDescription = contentDescription ?: "Action",
        modifier = iconModifier
    )

}