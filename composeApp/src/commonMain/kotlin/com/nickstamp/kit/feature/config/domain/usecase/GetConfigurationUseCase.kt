package com.nickstamp.kit.feature.config.domain.usecase

import com.nickstamp.kit.core.helpers.DateTimeHelper
import com.nickstamp.kit.feature.config.domain.model.Configuration
import com.nickstamp.kit.feature.config.domain.repository.ConfigurationRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class GetConfigurationUseCase(
    private val configurationRepository: ConfigurationRepository,
    private val dateTimeHelper: DateTimeHelper
) {
    private val mutex = Mutex()
    private var cachedConfiguration: Configuration? = null
    private var lastFetchTime: Long = 0
    private val cacheValidityMinutes = 30 // 30 minutes

    suspend operator fun invoke(forceFetch: Boolean = false): Configuration {
        return mutex.withLock {
            // If force fetch is requested, always fetch fresh configuration
            if (forceFetch) {
                return@withLock fetchFreshConfiguration()
            }

            // Return cached configuration if it's still valid
            cachedConfiguration?.let { cached ->
                if (isCacheValid()) {
                    return@withLock cached
                }
            }

            // Fetch new configuration from repository
            fetchFreshConfiguration()
        }
    }

    private suspend fun fetchFreshConfiguration(): Configuration {
        val freshConfiguration = configurationRepository.getConfiguration()
        cachedConfiguration = freshConfiguration
        lastFetchTime = dateTimeHelper.getCurrentTimeInMillis()
        return freshConfiguration
    }

    private fun isCacheValid(): Boolean {
        if (cachedConfiguration == null || lastFetchTime == 0L) {
            return false
        }
        
        val currentTime = dateTimeHelper.getCurrentTimeInMillis()
        val cacheValidityMillis = dateTimeHelper.minutesToMilliseconds(cacheValidityMinutes)
        
        return (currentTime - lastFetchTime) < cacheValidityMillis
    }
}