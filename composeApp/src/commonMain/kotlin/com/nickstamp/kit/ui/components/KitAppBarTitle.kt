package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.mediumEmphasis

@Composable
fun KitAppBarTitle(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: AnnotatedString? = null,
    onContainerColor: Color = colors.onSurface,
) {
    if (subtitle != null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.xsmall),
            modifier = modifier
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = typography.bold14.copy(color = onContainerColor),
            )
            Text(
                text = subtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = typography.regular12.copy(color = onContainerColor.mediumEmphasis()),
            )
        }
    } else {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = typography.bold18.copy(color = onContainerColor),
            modifier = modifier
        )
    }
}