package com.nickstamp.kit.presentation.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.presentation.shared.components.ImageSource
import com.nickstamp.kit.presentation.shared.components.KitAppBarIcon
import com.nickstamp.kit.presentation.shared.components.KitAppBarLogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: AnnotatedString? = null,
    navigateUp: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onContainerColor: Color = MaterialTheme.colorScheme.onPrimary,
    shape: Shape = AppTheme.shapes.bottomRoundedLarge,
    actions: @Composable RowScope.() -> Unit = {},
    logo: ImageSource? = null,
) {
    TopAppBar(
        navigationIcon = {
            if (navigateUp != null) {
                KitAppBarIcon(
                    onClickAction = navigateUp,
                    tint = onContainerColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = onContainerColor,
            navigationIconContentColor = onContainerColor,
            actionIconContentColor = onContainerColor
        ),
        actions = actions,
        title = {
            logo?.let {
                KitAppBarLogo(imageSource = it)
                Spacer(modifier = Modifier.width(AppTheme.spacing.default))
            }

            if (subtitle != null) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.xsmall),
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = AppTheme.typography.bold14.copy(color = onContainerColor),
                    )
                    Text(
                        text = subtitle,
                        maxLines = 1,
                        style = AppTheme.typography.regular12.copy(
                            color = onContainerColor.copy(
                                alpha = 0.7f
                            )
                        ),
                    )
                }
            } else {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bold16.copy(color = onContainerColor)
                )
            }
        },
        modifier = modifier.clip(shape)
    )
}