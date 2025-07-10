package com.nickstamp.kit.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun HtmlWebView(
    html: String,
    modifier: Modifier,
    textSize: Int,
    textColor: Color
) {
    val webView = remember {
        val config = WKWebViewConfiguration()
        WKWebView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0), configuration = config)
    }

    val actualColor = "#" + textColor.toArgb().toHexString().takeLast(6)
    UIKitView(
        modifier = modifier,
        factory = { webView },
        update = { webView ->
            val htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, 'SF Pro Text', sans-serif;
                            font-size: ${textSize}px;
                            line-height: 1.5;
                            color: $actualColor;
                            margin: 0;
                            padding: 16px;
                            text-align: center;
                            -webkit-user-select: none;
                            -webkit-touch-callout: none;
                        }
                        p { margin: 0 0 16px 0; }
                        h1, h2, h3, h4, h5, h6 { margin: 0 0 16px 0; }
                        ul, ol { text-align: left; margin: 0 0 16px 0; }
                        a { color: #007AFF; text-decoration: none; }
                        strong, b { font-weight: 600; }
                    </style>
                </head>
                <body>
                    $html
                </body>
                </html>
            """.trimIndent()

            webView.loadHTMLString(htmlContent, baseURL = null)
        }
    )
}
