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
import kit_cmp.composeapp.generated.resources.no_data
import kit_cmp.composeapp.generated.resources.no_results
import kit_cmp.composeapp.generated.resources.try_new_search
import kit_cmp.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
            contentDescription = stringResource(Res.string.no_data),
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = stringResource(Res.string.no_results),
            textAlign = TextAlign.Center,
            style = typography.bold16.copy(color = colorScheme.onBackground.highEmphasis())
        )

        Spacer(modifier = Modifier.height(spacing.default))

        Text(
            text = stringResource(Res.string.try_new_search),
            style = typography.regular12.copy(color = colorScheme.onBackground.mediumEmphasis())
        )

    }
}