package com.nickstamp.kit.feature.config.domain.model

data class CtaAction(
    val text: String,
    val url: String,
    val enabled: Boolean = true
) {
    val hasRedirectUrl: Boolean get() = url.isNotBlank()
}