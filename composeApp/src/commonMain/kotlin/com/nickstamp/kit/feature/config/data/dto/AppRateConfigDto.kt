package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppRateConfigDto(
    @SerialName("e") internal val _enabled: Boolean? = false
) {
    val enabled: Boolean
        get() = _enabled ?: false
}