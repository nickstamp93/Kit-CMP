package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdPlacementConfigDto(
    @SerialName("i") internal val _id: String? = null,
    @SerialName("e") internal val _enabled: Boolean? = false,
    @SerialName("s") internal val _size: Int? = -1,
    @SerialName("d") internal val _debounce: Int? = -1,
    @SerialName("fo") internal val _firstOffset: Int? = -1,
    @SerialName("as") internal val _adSpacing: Int? = -1
) {
    val id: String
        get() = _id.orEmpty()
    val enabled: Boolean
        get() = _enabled ?: false
    val size: Int
        get() = _size ?: -1
    val debounceMinutes: Int
        get() = _debounce ?: -1
    val firstOffset: Int
        get() = _firstOffset ?: -1
    val adSpacing: Int
        get() = _adSpacing ?: -1
}