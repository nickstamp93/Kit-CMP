package com.nickstamp.kit.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.components.ImageSource.Companion.toImageSource
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitAppBarLogo(
    imageSource: ImageSource,
    modifier: Modifier = Modifier,
    shape: Shape? = shapes.default
) {
    when (imageSource) {
        is ImageSource.LocalResource -> {
            Image(
                painter = painterResource(imageSource.resId),
                contentDescription = "App Logo",
                modifier = modifier
                    .size(40.dp)
                    .padding(spacing.small)
            )
        }

        is ImageSource.Url -> {
            KitNetworkImage(
                imageSource = imageSource.url.toImageSource(),
                contentScale = ContentScale.Fit,
                shape = shape,
                contentDescription = "App Logo",
                modifier = modifier
                    .size(40.dp)
                    .padding(spacing.small)
            )
        }
    }
}