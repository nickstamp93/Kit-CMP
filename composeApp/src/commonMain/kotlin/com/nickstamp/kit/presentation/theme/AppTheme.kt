package com.nickstamp.kit.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (useDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    CompositionLocalProvider(
        LocalTypography provides Typography(),
        LocalOpacity provides Opacity(),
        LocalSpacing provides Dimens(),
        LocalRadius provides Radius(),
        LocalShapes provides Shapes(),
        LocalElevation provides Elevation()
    ) {
        MaterialTheme(
            colorScheme = colorScheme
        ) {
            content()
        }
    }
}

object AppTheme {

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val opacity: Opacity
        @Composable
        @ReadOnlyComposable
        get() = LocalOpacity.current

    val spacing: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = LocalRadius.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val elevation: Elevation
        @Composable
        @ReadOnlyComposable
        get() = LocalElevation.current

}