package com.nickstamp.kit.feature.config.data.repository

import com.nickstamp.kit.core.network.ApiResult
import com.nickstamp.kit.feature.config.data.datasource.local.ConfigurationLocalDataSource
import com.nickstamp.kit.feature.config.data.datasource.remote.ConfigurationRemoteDataSource
import com.nickstamp.kit.feature.config.data.mapper.toDomain
import com.nickstamp.kit.feature.config.domain.model.Configuration
import com.nickstamp.kit.feature.config.domain.repository.ConfigurationRepository

class ConfigurationRepositoryImpl(
    private val remoteDataSource: ConfigurationRemoteDataSource,
    private val localDataSource: ConfigurationLocalDataSource
) : ConfigurationRepository {

    override suspend fun getConfiguration(): Configuration {
        return when (val result = remoteDataSource.getConfiguration()) {
            is ApiResult.Success -> {
                // Cache the successful result
                localDataSource.saveConfiguration(result.data)
                result.data.toDomain()
            }

            is ApiResult.Error -> {
                // Fallback to cached configuration
                localDataSource.getConfiguration()?.toDomain()
                    ?: throw result.exception
            }
        }
    }

    override suspend fun getCachedConfiguration(): Configuration? {
        return localDataSource.getConfiguration()?.toDomain()
    }

    override suspend fun refreshConfiguration(): Configuration {
        return when (val result = remoteDataSource.getConfiguration()) {
            is ApiResult.Success -> {
                // Cache the refreshed result
                localDataSource.saveConfiguration(result.data)
                result.data.toDomain()
            }

            is ApiResult.Error -> {
                throw result.exception
            }
        }
    }

}