package com.nickstamp.kit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.PreviewWrapper
import com.nickstamp.kit.ui.theme.highEmphasis
import com.nickstamp.kit.ui.theme.lowEmphasis
import com.nickstamp.kit.ui.theme.mediumEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun KitSearchCatalogButtonRow(
    hintText: String,
    onSearchClick: () -> Unit,
    catalogIconRes: DrawableResource,
    onCatalogClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.default),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .padding(
                horizontal = spacing.large
            )
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(shape = shapes.rounded50)
                .border(
                    width = 2.dp,
                    color = colors.onBackground.lowEmphasis(),
                    shape = shapes.rounded50
                )
                .clickable(
                    onClick = onSearchClick
                )
        ) {
            KitTextWithIcon(
                text = hintText,
                icon = Res.drawable.ic_search,
                tint = colors.onBackground.mediumEmphasis(),
                iconSize = 20.dp,
                style = typography.regular12.copy(color = colors.onBackground.mediumEmphasis()),
                modifier = Modifier
                    .padding(
                        vertical = spacing.medium,
                        horizontal = spacing.large
                    )
            )
        }

        Icon(
            painter = painterResource(catalogIconRes),
            tint = colors.onSurface.highEmphasis(),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .background(color = colors.surface, shape = shapes.default)
                .padding(spacing.default)
                .clickable(
                    onClick = onCatalogClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false)
                )
        )
    }
}

@Composable
fun SearchCatalogButtonRow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KitSearchCatalogButtonRow(
        hintText = "Search...",
        onSearchClick = onClick,
        catalogIconRes = Res.drawable.ic_search,
        onCatalogClick = onClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun KitSearchCatalogButtonRowPreview() {
    PreviewWrapper {
        KitSearchCatalogButtonRow(
            hintText = "Search",
            onSearchClick = {},
            catalogIconRes = Res.drawable.ic_search,
            onCatalogClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colors.background)
                .padding(spacing.large)
        )
    }
}