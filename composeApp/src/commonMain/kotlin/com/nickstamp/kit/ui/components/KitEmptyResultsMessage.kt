package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.highEmphasis
import com.nickstamp.kit.ui.theme.mediumEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitEmptyResultsMessage(
    modifier: Modifier = Modifier,
    icon: DrawableResource = Res.drawable.ic_info,
    iconTint: Color = colorScheme.onBackground.highEmphasis()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(icon),
            tint = iconTint,
            contentDescription = "No data",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = "Sorry, no results found",
            textAlign = TextAlign.Center,
            style = typography.bold16.copy(color = colorScheme.onBackground.highEmphasis())
        )

        Spacer(modifier = Modifier.height(spacing.default))

        Text(
            text = "Try a new search",
            style = typography.regular12.copy(color = colorScheme.onBackground.mediumEmphasis())
        )

    }
}