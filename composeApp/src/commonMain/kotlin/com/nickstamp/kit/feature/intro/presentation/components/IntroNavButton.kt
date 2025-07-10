package com.nickstamp.kit.feature.intro.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.lowEmphasis
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun IntroNavButton(
    iconRes: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg = Color.White.lowEmphasis()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(56.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
            )
            .drawBehind {
                drawCircle(color = bg)
            }
    ) {
        Icon(
            painter = painterResource(iconRes),
            tint = Color.White,
            contentDescription = null
        )
    }
}