package com.nickstamp.kit.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {
    @Serializable
    data object AppLauncher : AppRoutes()
    
    @Serializable
    data object Settings : AppRoutes()
    
    @Serializable
    data object Showcase : AppRoutes()
    
    @Serializable
    data object Intro : AppRoutes()
}