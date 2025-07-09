package com.nickstamp.kit.feature.config.data.datasource.local

import com.nickstamp.kit.core.storage.DatastoreManager
import com.nickstamp.kit.feature.config.data.dto.ConfigurationDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ConfigurationLocalDataSourceImpl(
    private val datastoreManager: DatastoreManager
) : ConfigurationLocalDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getConfiguration(): ConfigurationDto? {
        return try {
            val configString = datastoreManager.getString(CONFIG_KEY)
            configString?.let { json.decodeFromString<ConfigurationDto>(it) }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveConfiguration(configuration: ConfigurationDto) {
        try {
            val configString = json.encodeToString(configuration)
            datastoreManager.saveString(CONFIG_KEY, configString)
        } catch (e: Exception) {
            // Log error but don't crash
        }
    }

    override suspend fun clearConfiguration() {
        datastoreManager.remove(CONFIG_KEY)
    }

    companion object {
        private const val CONFIG_KEY = "configuration_cache"
    }
}