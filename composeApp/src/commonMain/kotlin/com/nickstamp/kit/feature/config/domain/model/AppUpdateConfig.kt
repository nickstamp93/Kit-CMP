package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PlatformUpdateConfig(
    val minimumRequiredVersion: Int,
    val latestVersion: Int,
    val downloadUrl: String
)

@Serializable
data class AppUpdateConfig(
    val ios: PlatformUpdateConfig,
    val android: PlatformUpdateConfig
)