package com.nickstamp.kit.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.typography

@Composable
fun KitBannerAdPlaceholder(modifier: Modifier = Modifier) {
    Text(
        text = "Advertisement",
        style = typography.regular14.copy(color = colors.onBackground.copy(alpha = 0.6f)),
        modifier = modifier
    )
}