package com.nickstamp.kit.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme.elevation
import com.nickstamp.kit.presentation.theme.AppTheme.shapes
import com.nickstamp.kit.presentation.theme.AppTheme.spacing
import com.nickstamp.kit.presentation.theme.AppTheme.typography
import com.nickstamp.kit.presentation.theme.mediumEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitEmptyDataViewCard(
    modifier: Modifier = Modifier,
    onTryAgainClick: (() -> Unit)? = null,
    text: String = "No data available",
    icon: DrawableResource = Res.drawable.ic_info,
    tint: Color = colorScheme.onBackground.mediumEmphasis()
) {
    Surface(
        shape = shapes.large,
        shadowElevation = elevation.small,
        modifier = modifier
            .wrapContentSize()
            .padding(spacing.large)
    ) {
        KitEmptyDataView(
            onActionClick = onTryAgainClick,
            text = text,
            icon = icon,
            tint = tint,
            modifier = Modifier
                .padding(
                    vertical = spacing.large,
                    horizontal = spacing.xxlarge
                )
        )
    }
}

@Composable
fun KitEmptyDataView(
    modifier: Modifier = Modifier,
    actionText: String = "Try again",
    onActionClick: (() -> Unit)? = null,
    text: String = "No data available",
    icon: DrawableResource = Res.drawable.ic_info,
    tint: Color = colorScheme.onBackground.mediumEmphasis()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            tint = tint,
            modifier = Modifier.size(56.dp)
        )
        Text(
            text = text,
            style = typography.bold16.copy(color = tint),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(spacing.large)
        )

        onActionClick?.let {
            Button(
                onClick = it,
                modifier = Modifier
            ) {
                Text(text = actionText)
            }
        }
    }
}

