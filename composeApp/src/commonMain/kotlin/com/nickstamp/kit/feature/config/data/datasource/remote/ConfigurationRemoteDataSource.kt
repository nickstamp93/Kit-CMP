package com.nickstamp.kit.feature.config.data.datasource.remote

import com.nickstamp.kit.core.network.ApiResult
import com.nickstamp.kit.feature.config.data.dto.ConfigurationDto
import com.nickstamp.kit.feature.config.data.service.ConfigurationService

class ConfigurationRemoteDataSource(
    private val configurationService: ConfigurationService
) {

    suspend fun getConfiguration(): ApiResult<ConfigurationDto> {
        return configurationService.getConfiguration()
    }

}