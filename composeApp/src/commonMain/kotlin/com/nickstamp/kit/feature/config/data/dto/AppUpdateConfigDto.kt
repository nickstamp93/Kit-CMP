package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppUpdateConfigDto(
    @SerialName("mrv") internal val _minimumRequiredVersion: Int? = 0,
    @SerialName("lvg") internal val _latestVersionGoogle: Int? = 0,
    @SerialName("lvh") internal val _latestVersionHuawei: Int? = 0,
    @SerialName("lvc") internal val _latestVersionCDN: Int? = 0,
    @SerialName("cu") internal val _cdnApkUrl: String? = "",
) {
    val minimumRequiredVersion: Int
        get() = _minimumRequiredVersion ?: 0
    val latestVersionGoogle: Int
        get() = _latestVersionGoogle ?: 0
    val latestVersionHuawei: Int
        get() = _latestVersionHuawei ?: 0
    val latestVersionCDN: Int
        get() = _latestVersionCDN ?: 0
    val cdnApkUrl: String
        get() = _cdnApkUrl.orEmpty()
}