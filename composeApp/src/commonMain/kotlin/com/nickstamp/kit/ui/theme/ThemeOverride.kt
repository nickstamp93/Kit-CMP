package com.nickstamp.kit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Theme override modes for specific screens
 */
enum class ThemeOverride {
    /**
     * Use the user's selected theme preference
     */
    UserPreference,
    
    /**
     * Force light theme regardless of user preference
     */
    Light,
    
    /**
     * Force dark theme regardless of user preference
     */
    ForcedDark
}

/**
 * CompositionLocal for theme override
 */
private val LocalThemeOverride = staticCompositionLocalOf { ThemeOverride.UserPreference }

/**
 * Provider for theme override context
 */
@Composable
fun ThemeOverrideProvider(
    override: ThemeOverride,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalThemeOverride provides override,
        content = content
    )
}

/**
 * Get the current theme override setting
 */
val currentThemeOverride: ThemeOverride
    @Composable
    get() = LocalThemeOverride.current

/**
 * Determine if dark theme should be used based on user preference and override
 */
@Composable
fun shouldUseDarkTheme(
    userPrefersDark: Boolean,
    override: ThemeOverride = currentThemeOverride
): Boolean {
    return when (override) {
        ThemeOverride.UserPreference -> userPrefersDark
        ThemeOverride.Light -> false
        ThemeOverride.ForcedDark -> true
    }
}

/**
 * Composable that applies theme with override support
 */
@Composable
fun ThemedContent(
    override: ThemeOverride,
    content: @Composable () -> Unit
) {
    val useDarkTheme = when (override) {
        ThemeOverride.UserPreference -> androidx.compose.foundation.isSystemInDarkTheme()
        ThemeOverride.Light -> false
        ThemeOverride.ForcedDark -> true
    }
    
    ThemeOverrideProvider(override = override) {
        AppTheme(useDarkTheme = useDarkTheme) {
            content()
        }
    }
}