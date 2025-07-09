package com.nickstamp.kit.feature.config.data.service

import com.nickstamp.kit.core.network.ApiResult
import com.nickstamp.kit.core.network.ApiService
import com.nickstamp.kit.core.network.safeApiCall
import com.nickstamp.kit.feature.config.data.dto.ApiResponse
import com.nickstamp.kit.feature.config.data.dto.ConfigurationDto
import com.nickstamp.kit.feature.config.data.endpoints.ConfigurationEndpoints

class ConfigurationService(
    private val apiService: ApiService
) {

    suspend fun getConfiguration(): ApiResult<ConfigurationDto> = safeApiCall {
        apiService.get< ApiResponse<ConfigurationDto>>(ConfigurationEndpoints.getConfiguration()).data
    }
}