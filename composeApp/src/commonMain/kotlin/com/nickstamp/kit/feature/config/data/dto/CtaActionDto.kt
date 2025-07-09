package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CtaActionDto(
    @SerialName("t") internal val _text: String? = "",
    @SerialName("u") internal val _url: String? = "",
    @SerialName("e") internal val _enabled: Boolean? = false
) {
    val text: String
        get() = _text.orEmpty()
    val url: String
        get() = _url.orEmpty()
    val enabled: Boolean
        get() = _enabled ?: false
}