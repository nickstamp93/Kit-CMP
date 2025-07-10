package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Announcement(
    val message: String,
    val action: CtaAction
)