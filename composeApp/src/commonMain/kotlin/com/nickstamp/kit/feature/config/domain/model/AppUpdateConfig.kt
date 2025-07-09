package com.nickstamp.kit.feature.config.domain.model

data class AppUpdateConfig(
    val minimumRequiredVersion: Int,
    val latestVersionGoogle: Int,
    val latestVersionHuawei: Int,
    val latestVersionCDN: Int,
    val cdnApkUrl: String
)