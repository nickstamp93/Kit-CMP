package com.nickstamp.kit.presentation.theme

import androidx.compose.ui.graphics.Color

internal object FlavorColorLightTokens {
    // Blue Primary Palette
    val Primary: Color = Color(0xFF1565C0)           // Blue 700
    val OnPrimary: Color = Color(0xFFFFFFFF)          // White
    val PrimaryContainer: Color = Color(0xFFE3F2FD)   // Blue 50
    val OnPrimaryContainer: Color = Color(0xFF0D47A1)  // Blue 800
    val PrimaryVariant: Color = Color(0xFF1976D2)     // Blue 600
    
    // Green Secondary Palette
    val Secondary: Color = Color(0xFF388E3C)          // Green 600
    val OnSecondary: Color = Color(0xFFFFFFFF)        // White
    val SecondaryContainer: Color = Color(0xFFE8F5E9) // Green 50
    val OnSecondaryContainer: Color = Color(0xFF1B5E20) // Green 800
    val SecondaryVariant: Color = Color(0xFF4CAF50)   // Green 500
    
    // Orange Tertiary Palette
    val Tertiary: Color = Color(0xFFE65100)           // Deep Orange 600
    val OnTertiary: Color = Color(0xFFFFFFFF)         // White
    val TertiaryContainer: Color = Color(0xFFFBE9E7)  // Deep Orange 50
    val OnTertiaryContainer: Color = Color(0xFFBF360C) // Deep Orange 800
}

internal object FlavorColorDarkTokens {
    // Blue Primary Palette (Dark)
    val Primary: Color = Color(0xFF90CAF9)            // Blue 200
    val OnPrimary: Color = Color(0xFF0D47A1)          // Blue 800
    val PrimaryContainer: Color = Color(0xFF1565C0)   // Blue 700
    val OnPrimaryContainer: Color = Color(0xFFE3F2FD) // Blue 50
    val PrimaryVariant: Color = Color(0xFF42A5F5)     // Blue 400
    
    // Green Secondary Palette (Dark)
    val Secondary: Color = Color(0xFF81C784)          // Green 300
    val OnSecondary: Color = Color(0xFF1B5E20)        // Green 800
    val SecondaryContainer: Color = Color(0xFF388E3C) // Green 600
    val OnSecondaryContainer: Color = Color(0xFFE8F5E9) // Green 50
    val SecondaryVariant: Color = Color(0xFF66BB6A)   // Green 400
    
    // Orange Tertiary Palette (Dark)
    val Tertiary: Color = Color(0xFFFFAB91)           // Deep Orange 200
    val OnTertiary: Color = Color(0xFFBF360C)         // Deep Orange 800
    val TertiaryContainer: Color = Color(0xFFE65100)  // Deep Orange 600
    val OnTertiaryContainer: Color = Color(0xFFFBE9E7) // Deep Orange 50
}

internal object LightColorTokens {
    // Neutral colors with better contrast
    val Background: Color = Color(0xFFF8F9FA)         // Light gray background
    val OnBackground: Color = Color(0xFF1A1C1E)       // Near black with blue undertone
    val Surface: Color = Color(0xFFFFFFFF)            // Pure white
    val OnSurface: Color = Color(0xFF1A1C1E)          // Near black
    val SurfaceContainer: Color = Color(0xFFE9ECEF)   // Medium light gray
    val OnSurfaceContainer: Color = Color(0xFF1A1C1E) // Near black
    val SurfaceVariant: Color = Color(0xFFDEE2E6)     // Light blue-gray
    val OnSurfaceVariant: Color = Color(0xFF44474F)   // Medium gray
    val Error: Color = Color(0xFFDC2626)              // Red 600
    val OnError: Color = Color(0xFFFFFFFF)            // White
    val ErrorContainer: Color = Color(0xFFFEF2F2)     // Red 50
    val Scrim: Color = Color(0x80000000)              // Semi-transparent black
}

internal object DarkColorTokens {
    // Dark neutral colors with blue undertones
    val Background: Color = Color(0xFF0F1419)         // Very dark blue-gray
    val OnBackground: Color = Color(0xFFE4E6EA)       // Light gray
    val Surface: Color = Color(0xFF1A1C1E)            // Dark gray with blue hint
    val OnSurface: Color = Color(0xFFE4E6EA)          // Light gray
    val SurfaceContainer: Color = Color(0xFF1F2937)   // Medium dark gray
    val SurfaceVariant: Color = Color(0xFF374151)     // Lighter dark gray
    val OnSurfaceVariant: Color = Color(0xFFD1D5DB)   // Medium light gray
    val Error: Color = Color(0xFFF87171)              // Red 400
    val OnError: Color = Color(0xFF7F1D1D)            // Red 800
    val ErrorContainer: Color = Color(0xFF7F1D1D)     // Red 800
    val Scrim: Color = Color(0x80000000)              // Semi-transparent black
}

// Semantic color roles - aligned with design system
internal object SemanticColorTokens {
    // Success colors (using green palette)
    val Success: Color = Color(0xFF16A34A)            // Green 600
    val OnSuccess: Color = Color(0xFFFFFFFF)          // White
    val SuccessContainer: Color = Color(0xFFDCFCE7)   // Green 100
    val OnSuccessContainer: Color = Color(0xFF14532D) // Green 800
    
    // Warning colors (using amber palette)
    val Warning: Color = Color(0xFFD97706)            // Amber 600
    val OnWarning: Color = Color(0xFFFFFFFF)          // White
    val WarningContainer: Color = Color(0xFFFEF3C7)   // Amber 100
    val OnWarningContainer: Color = Color(0xFF92400E) // Amber 800
    
    // Info colors (using blue palette)
    val Info: Color = Color(0xFF2563EB)               // Blue 600
    val OnInfo: Color = Color(0xFFFFFFFF)             // White
    val InfoContainer: Color = Color(0xFFDBEAFE)      // Blue 100
    val OnInfoContainer: Color = Color(0xFF1E3A8A)    // Blue 800
}

