package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformUpdateConfigDto(
    @SerialName("mrv") internal val _minimumRequiredVersion: Int? = 0,
    @SerialName("lv") internal val _latestVersion: Int? = 0,
    @SerialName("u") internal val _downloadUrl: String? = "",
) {
    val minimumRequiredVersion: Int
        get() = _minimumRequiredVersion ?: 0
    val latestVersion: Int
        get() = _latestVersion ?: 0
    val downloadUrl: String
        get() = _downloadUrl.orEmpty()
}

@Serializable
data class AppUpdateConfigDto(
    @SerialName("i") internal val _ios: PlatformUpdateConfigDto? = null,
    @SerialName("a") internal val _android: PlatformUpdateConfigDto? = null,
) {
    val ios: PlatformUpdateConfigDto
        get() = _ios ?: PlatformUpdateConfigDto()
    val android: PlatformUpdateConfigDto
        get() = _android ?: PlatformUpdateConfigDto()
}