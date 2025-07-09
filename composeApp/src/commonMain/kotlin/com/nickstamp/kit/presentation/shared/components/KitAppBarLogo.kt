package com.nickstamp.kit.presentation.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.presentation.shared.components.ImageSource.Companion.toImageSource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KitAppBarLogo(
    imageSource: ImageSource,
    modifier: Modifier = Modifier,
    shape: Shape? = AppTheme.shapes.default
) {
    when (imageSource) {
        is ImageSource.LocalResource -> {
            Image(
                painter = painterResource(imageSource.resId),
                contentDescription = "App Logo",
                modifier = modifier
                    .size(40.dp)
                    .padding(AppTheme.spacing.small)
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
                    .padding(AppTheme.spacing.small)
            )
        }
    }
}