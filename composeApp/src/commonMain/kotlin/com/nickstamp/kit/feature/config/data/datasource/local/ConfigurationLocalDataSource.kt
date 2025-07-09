package com.nickstamp.kit.feature.config.data.datasource.local

import com.nickstamp.kit.feature.config.data.dto.ConfigurationDto

interface ConfigurationLocalDataSource {
    suspend fun getConfiguration(): ConfigurationDto?
    suspend fun saveConfiguration(configuration: ConfigurationDto)
    suspend fun clearConfiguration()
}