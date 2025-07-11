package com.nickstamp.kit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nickstamp.kit.ui.theme.AppTheme
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class LabelSize(
    val iconSize: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp
) {
    XSMALL(
        iconSize = 12.dp,
        horizontalPadding = 6.dp,
        verticalPadding = 3.dp
    ),
    SMALL(
        iconSize = 16.dp,
        horizontalPadding = 8.dp,
        verticalPadding = 4.dp
    ),
    LARGE(
        iconSize = 16.dp,
        horizontalPadding = 10.dp,
        verticalPadding = 6.dp
    );

    val textStyle: TextStyle
        @Composable get() = when (this) {
            XSMALL -> typography.regular10
            SMALL -> typography.regular12
            LARGE -> typography.regular14
        }
}

@Composable
fun KitLabel(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = colors.surfaceVariant,
    textColor: Color = colors.onSurfaceVariant,
    iconStart: DrawableResource? = null,
    iconEnd: DrawableResource? = null,
    shape: Shape = shapes.rounded50,
    size: LabelSize = LabelSize.SMALL
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = containerColor, shape = shape)
            .padding(vertical = size.verticalPadding)
    ) {
        iconStart?.let {
            Spacer(modifier = Modifier.width(spacing.small))
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(size.iconSize)
            )
            Spacer(modifier = Modifier.width(spacing.small))
        } ?: kotlin.run {
            Spacer(modifier = Modifier.width(size.horizontalPadding))
        }

        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = size.textStyle.copy(color = textColor),
            modifier = Modifier
        )

        iconEnd?.let {
            Spacer(modifier = Modifier.width(spacing.small))
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(size.iconSize)
            )
            Spacer(modifier = Modifier.width(spacing.small))
        } ?: kotlin.run {
            Spacer(modifier = Modifier.width(size.horizontalPadding))
        }
    }
}