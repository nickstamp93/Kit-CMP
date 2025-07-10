package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.highEmphasis

@Composable
fun KitSectionContainer(
    title: String,
    modifier: Modifier = Modifier,
    titleHorizontalPadding: Dp = spacing.large,
    contentHorizontalPadding: Dp = spacing.none,
    action: @Composable (() -> Unit)? = null,
    iconRes: ImageVector? = null,
    iconTint: Color = colorScheme.onBackground.highEmphasis(),
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = titleHorizontalPadding)
        ) {
            iconRes?.let {
                Icon(
                    imageVector = iconRes,
                    tint = iconTint,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(spacing.default))
            }

            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = typography.bold18.copy(color = colorScheme.onBackground.highEmphasis()),
                modifier = Modifier.weight(1f)
            )

            action?.invoke()
        }

        Spacer(modifier = Modifier.height(spacing.default))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = contentHorizontalPadding)
        ) {
            content()
        }

    }

}

