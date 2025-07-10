package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppIntroConfig(
    val enabled: Boolean,
    val termsText: String
)