package com.nickstamp.kit.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Cross-platform WebView component for displaying HTML content
 */
@Composable
expect fun HtmlWebView(
    html: String,
    modifier: Modifier = Modifier
)