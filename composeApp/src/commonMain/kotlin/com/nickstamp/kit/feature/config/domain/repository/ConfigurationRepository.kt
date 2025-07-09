package com.nickstamp.kit.feature.config.domain.repository

import com.nickstamp.kit.feature.config.domain.model.Configuration

interface ConfigurationRepository {
    suspend fun getConfiguration(): Configuration
    suspend fun getCachedConfiguration(): Configuration?
    suspend fun refreshConfiguration(): Configuration
}