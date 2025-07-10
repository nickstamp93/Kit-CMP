package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppRateConfig(
    val enabled: Boolean
)