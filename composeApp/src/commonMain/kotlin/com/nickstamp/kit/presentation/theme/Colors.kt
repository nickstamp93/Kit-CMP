package com.nickstamp.kit.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.nickstamp.kit.presentation.theme.AppTheme.opacity

val LightColorScheme = lightColorScheme(
    primary = FlavorColorLightTokens.Primary,
    onPrimary = FlavorColorLightTokens.OnPrimary,
    secondary = FlavorColorLightTokens.Secondary,
    onSecondary = FlavorColorLightTokens.OnSecondary,
    background = LightColorTokens.Background,
    onBackground = LightColorTokens.OnBackground,
    surface = LightColorTokens.Surface,
    onSurface = LightColorTokens.OnSurface,
    error = LightColorTokens.Error,
    onError = LightColorTokens.OnError,
)

val DarkColorScheme = darkColorScheme(
    primary = FlavorColorLightTokens.Primary,
    onPrimary = FlavorColorLightTokens.OnPrimary,
    secondary = FlavorColorLightTokens.Secondary,
    onSecondary = FlavorColorLightTokens.OnSecondary,
    background = DarkColorTokens.Background,
    onBackground = DarkColorTokens.OnBackground,
    surface = DarkColorTokens.Surface,
    onSurface = DarkColorTokens.OnSurface,
    error = DarkColorTokens.Error,
    onError = DarkColorTokens.OnError,
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