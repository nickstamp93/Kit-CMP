package com.nickstamp.kit.feature.config.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CtaAction(
    val text: String,
    val url: String,
    val enabled: Boolean = true
) {
    val hasRedirectUrl: Boolean get() = url.isNotBlank()
}