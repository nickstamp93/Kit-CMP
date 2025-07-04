package com.nickstamp.kit.shared.components

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nickstamp.kit.presentation.theme.AppTheme

@Composable
fun KitBannerAdPlaceholder(modifier: Modifier = Modifier) {
    Text(
        text = "Advertisement",
        style = AppTheme.typography.regular14.copy(color = colorScheme.onBackground.copy(alpha = 0.6f)),
        modifier = modifier
    )
}