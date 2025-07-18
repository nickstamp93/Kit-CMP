package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T
)