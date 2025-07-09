package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppIntroConfigDto(
    @SerialName("e") internal val _enabled: Boolean? = false,
    @SerialName("t") internal val _termsText: String? = "",
) {
    val enabled: Boolean
        get() = _enabled ?: false
    val termsText: String
        get() = _termsText.orEmpty()
}