package com.nickstamp.kit.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class Opacity(
    val enabled: Float = 1f,
    val disabledLow: Float = 0.18f,
    val disabled: Float = 0.4f,
    val emphasisHighest: Float = 0.92f,
    val emphasisHigh: Float = 0.78f,
    val emphasisMedium: Float = 0.48f,
    val emphasisLow: Float = 0.18f,
    val emphasisLowest: Float = 0.05f,
) {
    fun from(enabled: Boolean) = if (enabled) this.enabled else this.disabledLow
}

internal val LocalOpacity = staticCompositionLocalOf { Opacity() }

