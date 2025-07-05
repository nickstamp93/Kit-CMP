package com.nickstamp.kit.shared.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun KitNetworkImage(
    imageSource: ImageSource,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape? = null,
    contentDescription: String? = null,
) {
    AsyncImage(
        model = imageSource.getData(),
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = if (shape != null) {
            modifier.clip(shape)
        } else {
            modifier
        }
    )
}

sealed interface ImageSource {
    fun getData(): Any
    fun getImageType(): ImageType
    
    data class LocalResource(val resId: DrawableResource) : ImageSource {
        override fun getData() = resId
        override fun getImageType() = ImageType.OTHER
    }

    data class Url(val url: String) : ImageSource {
        override fun getData() = url
        override fun getImageType() = ImageType.get(url)
    }

    companion object {
        fun DrawableResource.toImageSource() = LocalResource(this)
        fun String.toImageSource() = Url(this)
    }
}

enum class ImageType(
    private val suffix: String
) {
    GIF(suffix = "gif"),
    SVG(suffix = "svg"),
    OTHER(suffix = "");

    companion object {
        fun get(url: String): ImageType {
            return when {
                url.endsWith(GIF.suffix) -> GIF
                url.endsWith(SVG.suffix) -> SVG
                else -> OTHER
            }
        }
    }
}