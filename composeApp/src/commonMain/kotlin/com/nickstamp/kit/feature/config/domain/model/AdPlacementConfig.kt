package com.nickstamp.kit.feature.config.domain.model

data class AdPlacementConfig(
    val id: String,
    val enabled: Boolean,
    val size: Int,
    val debounceMinutes: Int,
    val firstOffset: Int,
    val adSpacing: Int
)