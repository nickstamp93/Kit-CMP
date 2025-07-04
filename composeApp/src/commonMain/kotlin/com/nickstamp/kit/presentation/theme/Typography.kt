package com.nickstamp.kit.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
data class Typography(
    val default: TextStyle = TextStyle(
    ),

    val bold36: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    val regular36: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    val boldItalic36: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    val regularItalic36Xxl: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp,
        lineHeight = 40.sp,
    ),
    val bold24: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
    ),
    val regular24: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
    ),
    val boldItalic24: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 24.sp,
        lineHeight = 24.sp,
    ),
    val regularItalic24: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 24.sp,
        lineHeight = 24.sp,
    ),
    val bold20: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    val regular20: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 20.sp,
    ),
    val boldItalic20: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp,
        lineHeight = 20.sp,
    ),
    val regularItalic20: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp,
        lineHeight = 20.sp,
    ),
    val bold18: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    ),
    val bold16: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    val regular16: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    val boldItalic16: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    val regularItalic16: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    val bold15: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val regular15: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val boldItalic15: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val regularItalic15: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    ),
    val bold14: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    val regular14: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    val boldItalic14: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    val regularItalic14: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        lineHeight = 16.sp,
    ),
    val bold12: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val regular12: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val boldItalic12: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val regularItalic12: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val bold11: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 12.sp,
    ),
    val regular11: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 12.sp,
    ),
    val boldItalic11: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 11.sp,
        lineHeight = 12.sp,
    ),
    val regularItalic11: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 11.sp,
        lineHeight = 12.sp,
    ),
    val bold10: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    ),
    val regular10: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    ),
    val boldItalic10: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    ),
    val regularItalic10: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 10.sp,
        lineHeight = 12.sp,
    ),
    val bold9: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp,
        lineHeight = 12.sp,
    ),
    val regular9: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        lineHeight = 12.sp,
    ),
    val boldItalic9: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 9.sp,
        lineHeight = 12.sp,
    ),
    val regularItalic9: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 9.sp,
        lineHeight = 12.sp,
    ),
    val bold8: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 8.sp,
        lineHeight = 12.sp,
    ),
    val regular8: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 12.sp,
    ),
    val boldItalic8: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 8.sp,
        lineHeight = 12.sp,
    ),
    val regularItalic8: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 8.sp,
        lineHeight = 12.sp,
    ),
)

internal val LocalTypography = staticCompositionLocalOf { Typography() }

