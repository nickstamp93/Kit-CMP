package com.nickstamp.kit.shared.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import com.nickstamp.kit.presentation.theme.AppTheme.shapes
import com.nickstamp.kit.presentation.theme.AppTheme.spacing
import com.nickstamp.kit.shared.components.ImageSource.Companion.toImageSource

@Composable
fun PaletteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    shape: Shape = shapes.default,
    fallbackColor: Color = colorScheme.surface,
) {
    var paletteColor: Color by remember(imageUrl) { mutableStateOf(fallbackColor) }

    PaletteColoredContainer(
        color = { paletteColor },
        modifier = modifier
    ) {
        KitNetworkImage(
            imageSource = imageUrl.toImageSource(),
            contentScale = ContentScale.Inside,
            shape = shape,
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.default)
                .align(Alignment.Center)
        )
    }
}