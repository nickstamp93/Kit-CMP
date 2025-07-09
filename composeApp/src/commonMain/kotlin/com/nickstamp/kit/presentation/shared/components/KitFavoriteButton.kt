package com.nickstamp.kit.presentation.shared.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_star_empty
import kit_cmp.composeapp.generated.resources.ic_star_filled
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    defaultColor: Color = colorScheme.onSurface,
    size: Dp = 40.dp,
    innerPadding: Dp = AppTheme.spacing.small
) {
    val enabledColor = colorScheme.primary

    val (icon, tint, description) = remember(isFavorite) {
        if (isFavorite) {
            Triple(Res.drawable.ic_star_filled, enabledColor, "Remove from favorites")
        } else {
            Triple(Res.drawable.ic_star_empty, defaultColor, "Add to favorites")
        }
    }

    var clicked by remember { mutableStateOf<Unit?>(null) }
    val scaleAnimation by animateFloatAsState(
        targetValue = if (clicked != null) 2f else 1f,
        label = "scale",
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        finishedListener = {
            clicked = null
        }
    )

    val rotateAnimation by animateFloatAsState(
        targetValue = if (isFavorite) 360f else 0f,
        label = "rotate",
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Icon(
        painter = painterResource(icon),
        tint = tint,
        contentDescription = description,
        modifier = modifier
            .size(size)
            .padding(innerPadding)
            .scale(scaleAnimation)
            .rotate(rotateAnimation)
            .clickable(
                onClick = {
                    clicked = Unit
                    onClick()
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = tint, bounded = false)
            )
    )
}