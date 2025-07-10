package com.nickstamp.kit.feature.applauncher.helper

import com.nickstamp.kit.core.helpers.SystemHelper
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStatus
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateStore
import com.nickstamp.kit.feature.applauncher.domain.AppUpdateType
import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig

class AppUpdateHelper(
    private val systemHelper: SystemHelper
) {

    private val currentVersion = systemHelper.getCurrentVersion()
    private val appStoreUrl = systemHelper.getAppStoreUrl()

    fun getAppUpdateStatus(updateConfig: AppUpdateConfig): AppUpdateStatus {

        println(updateConfig.toString())
        
        // Get platform-specific config
        val platformConfig = if (systemHelper.isAppleEnvironment()) {
            updateConfig.ios
        } else {
            updateConfig.android
        }

        // Determine update type based on minimum required version
        val updateType = when {
            currentVersion < platformConfig.minimumRequiredVersion -> AppUpdateType.MANDATORY
            else -> AppUpdateType.OPTIONAL
        }

        // Check if update is available
        val store = if (currentVersion < platformConfig.latestVersion) {
            // Use CDN URL if available, otherwise use official store URL
            val updateUrl = platformConfig.downloadUrl.ifEmpty { appStoreUrl }
            
            if (systemHelper.isAppleEnvironment()) {
                AppUpdateStore.Apple(updateUrl)
            } else {
                if (platformConfig.downloadUrl.isNotEmpty()) {
                    AppUpdateStore.CDN(updateUrl)
                } else {
                    AppUpdateStore.Google(updateUrl)
                }
            }
        } else {
            null
        }

        return when (store) {
            null -> AppUpdateStatus.UpToDate
            else -> AppUpdateStatus.UpdateAvailable(updateType, store)
        }
    }
}