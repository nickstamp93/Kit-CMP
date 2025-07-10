package com.nickstamp.kit.feature.config.domain

import com.nickstamp.kit.core.helpers.DateTimeHelper
import com.nickstamp.kit.feature.config.domain.model.Configuration
import com.nickstamp.kit.feature.config.domain.repository.ConfigurationRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Configurator(
    private val configurationRepository: ConfigurationRepository,
    private val dateTimeHelper: DateTimeHelper
) {
    private val mutex = Mutex()
    private var cachedConfiguration: Configuration? = null
    private var lastFetchTime: Long = 0
    private val cacheValidityMinutes = 30 // 30 minutes

    suspend fun getConfiguration(): Configuration {
        return mutex.withLock {
            // Return cached configuration if it's still valid
            cachedConfiguration?.let { cached ->
                if (isCacheValid()) {
                    println("Returning cached configuration")
                    return@withLock cached
                }
            }

            // Fetch new configuration from repository
            val freshConfiguration = configurationRepository.getConfiguration()
            cachedConfiguration = freshConfiguration
            lastFetchTime = dateTimeHelper.getCurrentTimeInMillis()

            println("Returning fresh configuration")
            freshConfiguration
        }
    }

    suspend fun getFreshConfiguration(): Configuration {
        return mutex.withLock {
            val freshConfiguration = configurationRepository.refreshConfiguration()
            cachedConfiguration = freshConfiguration
            lastFetchTime = dateTimeHelper.getCurrentTimeInMillis()

            freshConfiguration
        }
    }

    suspend fun getCachedConfiguration(): Configuration? {
        return mutex.withLock {
            cachedConfiguration
        }
    }

    private fun isCacheValid(): Boolean {
        if (cachedConfiguration == null || lastFetchTime == 0L) {
            return false
        }
        
        val currentTime = dateTimeHelper.getCurrentTimeInMillis()
        val cacheValidityMillis = dateTimeHelper.minutesToMilliseconds(cacheValidityMinutes)
        
        return (currentTime - lastFetchTime) < cacheValidityMillis
    }
    
    suspend fun isCacheValidPublic(): Boolean {
        return mutex.withLock {
            isCacheValid()
        }
    }

    suspend fun clearCache() {
        mutex.withLock {
            cachedConfiguration = null
            lastFetchTime = 0
        }
    }
    
    suspend fun getCachedConfigurationOrFetch(): Configuration {
        return mutex.withLock {
            cachedConfiguration?.takeIf { isCacheValid() } ?: run {
                val freshConfiguration = configurationRepository.getConfiguration()
                cachedConfiguration = freshConfiguration
                lastFetchTime = dateTimeHelper.getCurrentTimeInMillis()
                freshConfiguration
            }
        }
    }
    
    suspend fun getCacheInfo(): CacheInfo {
        return mutex.withLock {
            CacheInfo(
                hasCachedData = cachedConfiguration != null,
                lastFetchTime = lastFetchTime,
                isCacheValid = isCacheValid(),
                cacheValidityMinutes = cacheValidityMinutes
            )
        }
    }
    
    data class CacheInfo(
        val hasCachedData: Boolean,
        val lastFetchTime: Long,
        val isCacheValid: Boolean,
        val cacheValidityMinutes: Int
    )
}