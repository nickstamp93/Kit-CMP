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
import androidx.compose.material3.MaterialTheme.colorScheme
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
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.compose_multiplatform
import kit_cmp.composeapp.generated.resources.ic_back
import kit_cmp.composeapp.generated.resources.ic_dark_mode
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: AnnotatedString? = null,
    onBack: (() -> Unit)? = null,
    containerColor: Color = colorScheme.primary,
    onContainerColor: Color = colorScheme.onPrimary,
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
            }
        },
        modifier = modifier.clip(shape)
    )
}

@Preview
@Composable
private fun XAppBarPreview() {
    AppTheme {
        Column {
            KitAppBar()

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Title",
                onBack = null,
                logo = null
            )


            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Title",
                onBack = null,
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Title",
                onBack = { },
                logo = null
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Title",
                onBack = { },
                logo = Res.drawable.compose_multiplatform.toImageSource()
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Title",
                subtitle = AnnotatedString("Subtitle"),
                onBack = { },
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Really long text for the title to be",
                onBack = { },
                actions = {
                    KitAppBarIcon(
                        iconRes = Res.drawable.ic_dark_mode,
                        tint = colorScheme.error,
                        onClickAction = { }
                    )
                }
            )

            Spacer(modifier = Modifier.height(spacing.default))
            KitAppBar(
                title = "Really long text for the title to be",
                subtitle = AnnotatedString("Subtitle"),
                onBack = { },
                actions = {
                    KitAppBarIcon(
                        iconRes = Res.drawable.ic_dark_mode,
                        tint = colorScheme.error,
                        onClickAction = { }
                    )
                },
                modifier = Modifier.clip(shapes.bottomRoundedLarge)
            )
        }
    }
}