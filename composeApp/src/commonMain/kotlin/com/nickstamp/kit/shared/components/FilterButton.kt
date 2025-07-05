package com.nickstamp.kit.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme.shapes
import com.nickstamp.kit.presentation.theme.AppTheme.spacing
import com.nickstamp.kit.presentation.theme.AppTheme.typography
import com.nickstamp.kit.presentation.theme.highEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_arrow_down
import org.jetbrains.compose.resources.painterResource

@Composable
fun FilterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shapes.default)
            .border(
                width = 2.dp,
                color = colorScheme.surfaceVariant,
                shape = shapes.default
            )
            .background(
                color = colorScheme.surface
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = colorScheme.onSurface)
            )
            .padding(
                horizontal = spacing.default,
                vertical = spacing.default
            )
    ) {
        Text(
            text = text,
            style = typography.bold14.copy(color = colorScheme.onBackground.highEmphasis()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(spacing.small))

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_down),
            tint = colorScheme.onSurface.highEmphasis(),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

    }
}