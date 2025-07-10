package com.nickstamp.kit.ui.components

import android.util.TypedValue
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.nickstamp.kit.ui.theme.highEmphasis

@Composable
actual fun HtmlWebView(
    html: String,
    modifier: Modifier,
    textSize: Int,
    textColor: Color
) {
    val spannedText = remember(html) {
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
    val actualColor = textColor.toArgb()
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                setTextColor(actualColor)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            }
        },
        update = {
            it.text = spannedText
        },
        modifier = modifier,
    )
}
