package com.nickstamp.kit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_back
import kit_cmp.composeapp.generated.resources.ic_dark_mode
import kit_cmp.composeapp.generated.resources.preview_long_title
import kit_cmp.composeapp.generated.resources.preview_subtitle
import kit_cmp.composeapp.generated.resources.preview_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: AnnotatedString? = null,
    onBack: (() -> Unit)? = null,
    containerColor: Color = colors.primary,
    onContainerColor: Color = colors.onPrimary,
    shape: Shape = shapes.bottomRoundedLarge,
    actions: @Composable RowScope.() -> Unit = {},
    logo: ImageSource? = null,
) {
    TopAppBar(
        navigationIcon = {
            if (onBack != null) {
                KitAppBarIcon(
                    icon = Res.drawable.ic_back,
                    onClickAction = onBack,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.default),
            ) {
                logo?.let {
                    KitAppBarLogo(imageSource = it)
                }

                if (subtitle != null) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spacing.xsmall),
                    ) {
                        Text(
                            text = title,
                            maxLines = 1,
                            style = typography.bold14.copy(color = onContainerColor),
                        )
                        Text(
                            text = subtitle,
                            maxLines = 1,
                            style = typography.regular12.copy(
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
                        style = typography.bold16.copy(color = onContainerColor)
                    )
                }
            }
        },
        modifier = modifier.clip(shape)
    )
}

@Preview
@Composable
private fun XAppBarPreview() {
    PreviewWrapper {
        Column {
            KitAppBar()

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_title),
                onBack = null,
                logo = null
            )


            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_title),
                onBack = null,
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_title),
                onBack = { },
                logo = null
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_title),
                onBack = { },
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_title),
                subtitle = AnnotatedString(stringResource(Res.string.preview_subtitle)),
                onBack = { },
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_long_title),
                onBack = { },
                actions = {
                    KitAppBarIcon(
                        iconRes = Res.drawable.ic_dark_mode,
                        tint = colors.error,
                        onClickAction = { }
                    )
                }
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = stringResource(Res.string.preview_long_title),
                subtitle = AnnotatedString(stringResource(Res.string.preview_subtitle)),
                onBack = { },
                actions = {
                    KitAppBarIcon(
                        iconRes = Res.drawable.ic_dark_mode,
                        tint = colors.error,
                        onClickAction = { }
                    )
                },
                modifier = Modifier.clip(shapes.bottomRoundedLarge)
            )
        }
    }
}