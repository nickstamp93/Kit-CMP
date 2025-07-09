package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementDto(
    @SerialName("m") internal val _message: String? = "",
    @SerialName("a") internal val _action: CtaActionDto? = CtaActionDto(),
) {
    val message: String
        get() = _message.orEmpty()

    val action: CtaActionDto
        get() = _action ?: CtaActionDto()
}