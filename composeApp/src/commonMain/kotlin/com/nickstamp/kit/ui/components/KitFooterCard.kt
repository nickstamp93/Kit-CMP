package com.nickstamp.kit.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.elevation
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.highEmphasis
import com.nickstamp.kit.ui.theme.highestEmphasis
import com.nickstamp.kit.ui.theme.mediumEmphasis
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.theme.PreviewWrapper
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_info
import kit_cmp.composeapp.generated.resources.ic_next
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun KitFooterCard(
    title: String,
    primaryActionIcon: DrawableResource,
    backgroundImageUrl: String,
    primaryActionText: String,
    onPrimaryAction: () -> Unit,
    secondaryActionIcon: DrawableResource,
    secondaryActionText: String,
    onSecondaryActionText: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Surface(
        modifier = modifier,
        shape = shapes.large,
        shadowElevation = elevation.large,
        border = BorderStroke(
            width = 2.dp,
            color = colorScheme.surface
        )
    ) {

        Box(modifier = Modifier.height(IntrinsicSize.Min)) {

            backgroundImageUrl?.let {
                Box(
                    modifier = Modifier
                        .background(Color.Black.highEmphasis())
                        .clip(shapes.large)
                ) {
                    KitNetworkImage(
                        imageSource = it.toImageSource(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .alpha(0.4f)
                            .fillMaxSize()
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = spacing.large,
                        end = spacing.large,
                        top = spacing.xxxxxlarge,
                        bottom = spacing.large
                    )
            ) {

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = typography.regular20.copy(color = Color.White.highestEmphasis())
                )

                subtitle?.let {
                    Spacer(modifier = Modifier.height(spacing.default))
                    Text(
                        text = subtitle,
                        style = typography.regular12.copy(color = Color.White.mediumEmphasis())
                    )
                }

                Spacer(modifier = Modifier.height(spacing.xxlarge))

                Row {
                    Button(
                        onClick = onSecondaryActionText,
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = colorScheme.surface,
                            contentColor = colorScheme.onSurface
                        ),
                        shape = shapes.default,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(secondaryActionIcon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(spacing.small))
                        Text(
                            text = secondaryActionText,
                            style = typography.regular12,
                        )
                    }

                    Spacer(modifier = Modifier.width(spacing.default))

                    Button(
                        onClick = onPrimaryAction,
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = colorScheme.secondary,
                            contentColor = colorScheme.onSecondary
                        ),
                        shape = shapes.default,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(primaryActionIcon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(spacing.small))
                        Text(
                            text = primaryActionText,
                            style = typography.regular12,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun FooterCardPreview() {
    PreviewWrapper {
        KitFooterCard(
            title = "Want more?",
            subtitle = "Browse the whole cinema catalog",
            primaryActionText = "Movie Catalog",
            primaryActionIcon = Res.drawable.ic_next,
            onPrimaryAction = {},
            secondaryActionIcon = Res.drawable.ic_info,
            secondaryActionText = "Search by title",
            onSecondaryActionText = {},
            backgroundImageUrl = "",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorScheme.background)
                .padding(16.dp)
        )
    }
}