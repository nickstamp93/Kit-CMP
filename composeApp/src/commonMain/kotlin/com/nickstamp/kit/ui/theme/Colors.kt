package com.nickstamp.kit.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.ui.theme.AppTheme.opacity

val LightColorScheme = lightColorScheme(
    primary = FlavorColorLightTokens.Primary,
    onPrimary = FlavorColorLightTokens.OnPrimary,
    primaryContainer = FlavorColorLightTokens.PrimaryContainer,
    onPrimaryContainer = FlavorColorLightTokens.OnPrimaryContainer,
    secondary = FlavorColorLightTokens.Secondary,
    onSecondary = FlavorColorLightTokens.OnSecondary,
    secondaryContainer = FlavorColorLightTokens.SecondaryContainer,
    onSecondaryContainer = FlavorColorLightTokens.OnSecondaryContainer,
    tertiary = FlavorColorLightTokens.Tertiary,
    onTertiary = FlavorColorLightTokens.OnTertiary,
    tertiaryContainer = FlavorColorLightTokens.TertiaryContainer,
    onTertiaryContainer = FlavorColorLightTokens.OnTertiaryContainer,
    background = LightColorTokens.Background,
    onBackground = LightColorTokens.OnBackground,
    surface = LightColorTokens.Surface,
    onSurface = LightColorTokens.OnSurface,
    surfaceVariant = LightColorTokens.SurfaceVariant,
    onSurfaceVariant = LightColorTokens.OnSurfaceVariant,
    surfaceContainer = LightColorTokens.SurfaceContainer,
    error = LightColorTokens.Error,
    onError = LightColorTokens.OnError,
    errorContainer = LightColorTokens.ErrorContainer,
    scrim = LightColorTokens.Scrim,
)

val DarkColorScheme = darkColorScheme(
    primary = FlavorColorDarkTokens.Primary,
    onPrimary = FlavorColorDarkTokens.OnPrimary,
    primaryContainer = FlavorColorDarkTokens.PrimaryContainer,
    onPrimaryContainer = FlavorColorDarkTokens.OnPrimaryContainer,
    secondary = FlavorColorDarkTokens.Secondary,
    onSecondary = FlavorColorDarkTokens.OnSecondary,
    secondaryContainer = FlavorColorDarkTokens.SecondaryContainer,
    onSecondaryContainer = FlavorColorDarkTokens.OnSecondaryContainer,
    tertiary = FlavorColorDarkTokens.Tertiary,
    onTertiary = FlavorColorDarkTokens.OnTertiary,
    tertiaryContainer = FlavorColorDarkTokens.TertiaryContainer,
    onTertiaryContainer = FlavorColorDarkTokens.OnTertiaryContainer,
    background = DarkColorTokens.Background,
    onBackground = DarkColorTokens.OnBackground,
    surface = DarkColorTokens.Surface,
    onSurface = DarkColorTokens.OnSurface,
    surfaceVariant = DarkColorTokens.SurfaceVariant,
    onSurfaceVariant = DarkColorTokens.OnSurfaceVariant,
    surfaceContainer = DarkColorTokens.SurfaceContainer,
    error = DarkColorTokens.Error,
    onError = DarkColorTokens.OnError,
    errorContainer = DarkColorTokens.ErrorContainer,
    scrim = DarkColorTokens.Scrim,
)

@Composable
fun Color.highestEmphasis() = copy(alpha = opacity.emphasisHighest)

@Composable
fun Color.highEmphasis() = copy(alpha = opacity.emphasisHigh)

@Composable
fun Color.mediumEmphasis() = copy(alpha = opacity.emphasisMedium)

@Composable
fun Color.lowEmphasis() = copy(alpha = opacity.emphasisLow)

@Composable
fun Color.lowestEmphasis() = copy(alpha = opacity.emphasisLowest)

// Extended color scheme for semantic colors
@Immutable
data class ExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        success = SemanticColorTokens.Success,
        onSuccess = SemanticColorTokens.OnSuccess,
        successContainer = SemanticColorTokens.SuccessContainer,
        onSuccessContainer = SemanticColorTokens.OnSuccessContainer,
        warning = SemanticColorTokens.Warning,
        onWarning = SemanticColorTokens.OnWarning,
        warningContainer = SemanticColorTokens.WarningContainer,
        onWarningContainer = SemanticColorTokens.OnWarningContainer,
        info = SemanticColorTokens.Info,
        onInfo = SemanticColorTokens.OnInfo,
        infoContainer = SemanticColorTokens.InfoContainer,
        onInfoContainer = SemanticColorTokens.OnInfoContainer,
    )
}