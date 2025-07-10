package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AdPlacementConfig(
    val id: String,
    val enabled: Boolean,
    val size: Int,
    val debounceMinutes: Int,
    val firstOffset: Int,
    val adSpacing: Int
)