package com.nickstamp.kit.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {
    @Serializable
    data object Settings : AppRoutes()
    
    @Serializable
    data object Showcase : AppRoutes()
}