package com.nickstamp.kit.shared.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.nickstamp.kit.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun KitAppBarIcon(
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    icon: DrawableResource? = null,
    imageVector: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
    contentDescription: String? = null
) {
    KitIconButton(
        icon = icon,
        imageVector = imageVector,
        onClickAction = onClickAction,
        tint = tint,
        contentDescription = contentDescription,
        modifier = modifier.padding(AppTheme.spacing.large)
    )
}