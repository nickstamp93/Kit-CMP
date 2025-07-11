package com.nickstamp.kit.ui.theme


import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Unified color interface that provides a single way to access all colors in the design system.
 * This combines Material Design colors with extended semantic colors.
 */
interface PaletteColors {
    // Primary colors
    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color
    
    // Secondary colors
    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color
    
    // Tertiary colors
    val tertiary: Color
    val onTertiary: Color
    val tertiaryContainer: Color
    val onTertiaryContainer: Color
    
    // Error colors
    val error: Color
    val onError: Color
    val errorContainer: Color
    val onErrorContainer: Color
    
    // Success colors (extended)
    val success: Color
    val onSuccess: Color
    val successContainer: Color
    val onSuccessContainer: Color
    
    // Warning colors (extended)
    val warning: Color
    val onWarning: Color
    val warningContainer: Color
    val onWarningContainer: Color
    
    // Info colors (extended)
    val info: Color
    val onInfo: Color
    val infoContainer: Color
    val onInfoContainer: Color
    
    // Surface colors
    val surface: Color
    val onSurface: Color
    val surfaceVariant: Color
    val onSurfaceVariant: Color
    val surfaceTint: Color
    val inverseSurface: Color
    val inverseOnSurface: Color
    
    // Background colors
    val background: Color
    val onBackground: Color
    
    // Outline colors
    val outline: Color
    val outlineVariant: Color
    
    // Scrim
    val scrim: Color
    
    // Surface containers
    val surfaceDim: Color
    val surfaceBright: Color
    val surfaceContainerLowest: Color
    val surfaceContainerLow: Color
    val surfaceContainer: Color
    val surfaceContainerHigh: Color
    val surfaceContainerHighest: Color
    
    // Inverse colors
    val inversePrimary: Color
}

/**
 * Implementation of UnifiedColors that combines Material ColorScheme with ExtendedColors
 */
private data class PaletteColorsImpl(
    private val colorScheme: ColorScheme,
    private val extendedColors: ExtendedColors
) : PaletteColors {
    
    // Primary colors
    override val primary: Color get() = colorScheme.primary
    override val onPrimary: Color get() = colorScheme.onPrimary
    override val primaryContainer: Color get() = colorScheme.primaryContainer
    override val onPrimaryContainer: Color get() = colorScheme.onPrimaryContainer
    
    // Secondary colors
    override val secondary: Color get() = colorScheme.secondary
    override val onSecondary: Color get() = colorScheme.onSecondary
    override val secondaryContainer: Color get() = colorScheme.secondaryContainer
    override val onSecondaryContainer: Color get() = colorScheme.onSecondaryContainer
    
    // Tertiary colors
    override val tertiary: Color get() = colorScheme.tertiary
    override val onTertiary: Color get() = colorScheme.onTertiary
    override val tertiaryContainer: Color get() = colorScheme.tertiaryContainer
    override val onTertiaryContainer: Color get() = colorScheme.onTertiaryContainer
    
    // Error colors
    override val error: Color get() = colorScheme.error
    override val onError: Color get() = colorScheme.onError
    override val errorContainer: Color get() = colorScheme.errorContainer
    override val onErrorContainer: Color get() = colorScheme.onErrorContainer
    
    // Success colors (extended)
    override val success: Color get() = extendedColors.success
    override val onSuccess: Color get() = extendedColors.onSuccess
    override val successContainer: Color get() = extendedColors.successContainer
    override val onSuccessContainer: Color get() = extendedColors.onSuccessContainer
    
    // Warning colors (extended)
    override val warning: Color get() = extendedColors.warning
    override val onWarning: Color get() = extendedColors.onWarning
    override val warningContainer: Color get() = extendedColors.warningContainer
    override val onWarningContainer: Color get() = extendedColors.onWarningContainer
    
    // Info colors (extended)
    override val info: Color get() = extendedColors.info
    override val onInfo: Color get() = extendedColors.onInfo
    override val infoContainer: Color get() = extendedColors.infoContainer
    override val onInfoContainer: Color get() = extendedColors.onInfoContainer
    
    // Surface colors
    override val surface: Color get() = colorScheme.surface
    override val onSurface: Color get() = colorScheme.onSurface
    override val surfaceVariant: Color get() = colorScheme.surfaceVariant
    override val onSurfaceVariant: Color get() = colorScheme.onSurfaceVariant
    override val surfaceTint: Color get() = colorScheme.surfaceTint
    override val inverseSurface: Color get() = colorScheme.inverseSurface
    override val inverseOnSurface: Color get() = colorScheme.inverseOnSurface
    
    // Background colors
    override val background: Color get() = colorScheme.background
    override val onBackground: Color get() = colorScheme.onBackground
    
    // Outline colors
    override val outline: Color get() = colorScheme.outline
    override val outlineVariant: Color get() = colorScheme.outlineVariant
    
    // Scrim
    override val scrim: Color get() = colorScheme.scrim
    
    // Surface containers
    override val surfaceDim: Color get() = colorScheme.surfaceDim
    override val surfaceBright: Color get() = colorScheme.surfaceBright
    override val surfaceContainerLowest: Color get() = colorScheme.surfaceContainerLowest
    override val surfaceContainerLow: Color get() = colorScheme.surfaceContainerLow
    override val surfaceContainer: Color get() = colorScheme.surfaceContainer
    override val surfaceContainerHigh: Color get() = colorScheme.surfaceContainerHigh
    override val surfaceContainerHighest: Color get() = colorScheme.surfaceContainerHighest
    
    // Inverse colors
    override val inversePrimary: Color get() = colorScheme.inversePrimary
}

/**
 * Creates a unified color interface that combines Material and Extended colors
 */
@Composable
internal fun paletteColors(
    colorScheme: ColorScheme,
    extendedColors: ExtendedColors
): PaletteColors = PaletteColorsImpl(colorScheme, extendedColors)