package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppUpdateConfig(
    val minimumRequiredVersion: Int,
    val latestVersionGoogle: Int,
    val latestVersionApple: Int,
    val latestVersionCDN: Int,
    val cdnApkUrl: String
)