package com.nickstamp.kit.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.ui.theme.highEmphasis

/**
 * Cross-platform WebView component for displaying HTML content
 */
@Composable
expect fun HtmlWebView(
    html: String,
    modifier: Modifier = Modifier,
    textSize: Int = 14,
    textColor: Color = Color.Black.highEmphasis()
)