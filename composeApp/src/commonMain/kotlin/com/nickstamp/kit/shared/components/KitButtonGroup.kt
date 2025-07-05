package com.nickstamp.kit.shared.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.presentation.theme.AppTheme
import com.nickstamp.kit.presentation.theme.AppTheme.shapes

@Composable
fun KitButtonGroupRow(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.default,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    contents: @Composable RowScope.() -> Unit
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        modifier = modifier.clip(shape)
    ) {
        contents()
    }
}

@Composable
fun KitButtonGroupColumn(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.default,
    contents: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.clip(shape)
    ) {
        contents()
    }
}

data class KitGroupButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledSelectedContainerColor: Color,
    val disabledSelectedContentColor: Color
)

object KitGroupButtonDefaults {


    @Composable
    fun primaryColors() = KitGroupButtonColors(
        containerColor = colorScheme.primaryContainer,
        contentColor = colorScheme.onPrimaryContainer,
        selectedContainerColor = colorScheme.primary,
        selectedContentColor = colorScheme.onPrimary,
        disabledContainerColor = colorScheme.primaryContainer.copy(alpha = 0.6f),
        disabledContentColor = colorScheme.onPrimary.copy(alpha = 0.8f),
        disabledSelectedContainerColor = colorScheme.primary.copy(alpha = 0.8f),
        disabledSelectedContentColor = colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
    )

    @Composable
    fun secondaryColors() = KitGroupButtonColors(
        containerColor = colorScheme.surface,
        contentColor = colorScheme.onSurface,
        selectedContainerColor = colorScheme.secondary,
        selectedContentColor = colorScheme.onSecondary,
        disabledContainerColor = colorScheme.surfaceVariant.copy(alpha = 0.6f),
        disabledContentColor = colorScheme.onSurface.copy(alpha = 0.8f),
        disabledSelectedContainerColor = colorScheme.secondary.copy(alpha = 0.6f),
        disabledSelectedContentColor = colorScheme.onSecondary.copy(alpha = 0.8f)
    )
}

sealed class KitGroupButtonSize {
    @Composable
    abstract fun getSizes(): KitGroupButtonSizes

    data object Small : KitGroupButtonSize() {

        @Composable
        override fun getSizes(): KitGroupButtonSizes {
            return KitGroupButtonSizes(
                textStyle = AppTheme.typography.bold10,
                iconSize = 10.dp,
            )
        }
    }

    data object Medium : KitGroupButtonSize() {

        @Composable
        override fun getSizes(): KitGroupButtonSizes {
            return KitGroupButtonSizes(
                textStyle = AppTheme.typography.bold12,
                iconSize = 12.dp,
            )
        }
    }

    data object Large : KitGroupButtonSize() {

        @Composable
        override fun getSizes(): KitGroupButtonSizes {
            return KitGroupButtonSizes(
                textStyle = AppTheme.typography.bold14,
                iconSize = 14.dp,
            )
        }
    }
}

data class KitGroupButtonSizes(
    val textStyle: TextStyle,
    val iconSize: Dp,
)

@Composable
fun KitGroupButton(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    size: KitGroupButtonSize = KitGroupButtonSize.Small,
    enabled: Boolean = true,
    colors: KitGroupButtonColors = KitGroupButtonDefaults.primaryColors(),
    icon: ImageVector? = null,
    contentPadding: PaddingValues = PaddingValues(AppTheme.spacing.medium),
    onClick: () -> Unit = {}
) {
    val sizes = size.getSizes()

    val containerColor = animateColorAsState(
        targetValue = when {
            enabled -> {
                if (selected) colors.selectedContainerColor
                else colors.containerColor
            }

            else -> {
                if (selected) colors.disabledSelectedContainerColor
                else colors.disabledContainerColor

            }
        },
        label = "BgColorAnimation"
    )
    val contentColor = animateColorAsState(
        targetValue = when {
            enabled -> {
                if (selected) colors.selectedContentColor
                else colors.contentColor
            }

            else -> {
                if (selected) colors.disabledSelectedContentColor
                else colors.disabledContentColor
            }
        },
        label = "TextColorAnimation"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .drawBehind {
                drawRect(color = containerColor.value)
            }
            .padding(paddingValues = contentPadding)
    ) {

        icon?.let {
            Icon(
                imageVector = icon,
                tint = contentColor.value,
                contentDescription = null,
                modifier = Modifier.size(sizes.iconSize)
            )

            Spacer(modifier = Modifier.width(AppTheme.spacing.small))
        }

        Text(
            text = text,
            style = sizes.textStyle.copy(color = contentColor.value),
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier
        )
    }

}

@Composable
fun KitGroupIconButton(
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    KitGroupButtonContainer(
        selected = selected,
        onClick = onClick,

        content = { color ->
            Icon(
                imageVector = icon,
                tint = color.value,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        modifier = modifier
    )

}

@Composable
fun KitGroupButtonContainer(
    content: @Composable RowScope.(State<Color>) -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: KitGroupButtonColors = KitGroupButtonDefaults.primaryColors(),
    contentPadding: PaddingValues = PaddingValues(AppTheme.spacing.medium),
    onClick: () -> Unit = {}
) {

    val containerColor = animateColorAsState(
        targetValue = when {
            enabled -> {
                if (selected) colors.selectedContainerColor
                else colors.containerColor
            }

            else -> {
                if (selected) colors.disabledSelectedContainerColor
                else colors.disabledContainerColor

            }
        },
        label = "BgColorAnimation"
    )
    val contentColor = animateColorAsState(
        targetValue = when {
            enabled -> {
                if (selected) colors.selectedContentColor
                else colors.contentColor
            }

            else -> {
                if (selected) colors.disabledSelectedContentColor
                else colors.disabledContentColor
            }
        },
        label = "TextColorAnimation"
    )

    Row(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .drawBehind {
                drawRect(color = containerColor.value)
            }
            .padding(paddingValues = contentPadding)
    ) {
        content(contentColor)
    }
}




