package com.nickstamp.kit.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.nickstamp.kit.ui.theme.AppTheme.colors

@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = colors.background)
        ) {
            content()
        }
    }
}

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
    
    val extendedColors = ExtendedColors(
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
    
    CompositionLocalProvider(
        LocalTypography provides Typography(),
        LocalOpacity provides Opacity(),
        LocalSpacing provides Dimens(),
        LocalRadius provides Radius(),
        LocalShapes provides Shapes(),
        LocalElevation provides Elevation(),
        LocalPaletteColors provides paletteColors(colorScheme, extendedColors)
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

    val colors: PaletteColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPaletteColors.current

}

// CompositionLocal for UnifiedColors
private val LocalPaletteColors = staticCompositionLocalOf<PaletteColors> {
    error("No UnifiedColors provided")
}