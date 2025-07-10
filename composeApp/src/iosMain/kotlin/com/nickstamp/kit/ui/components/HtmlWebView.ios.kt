package com.nickstamp.kit.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun HtmlWebView(
    html: String,
    modifier: Modifier
) {
    UIKitView(
        factory = { WKWebView() },
        modifier = modifier,
        update = { webView ->
            webView.loadHTMLString(html, null)
        }
    )
}
