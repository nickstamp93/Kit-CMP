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
        val updateType = when {
            currentVersion < updateConfig.minimumRequiredVersion -> AppUpdateType.MANDATORY
            else -> AppUpdateType.OPTIONAL
        }

        val latestStoreVersion = if (systemHelper.isAppleEnvironment()) {
            updateConfig.latestVersionApple
        } else {
            updateConfig.latestVersionGoogle
        }

        val store = if (currentVersion < latestStoreVersion && systemHelper.isAppleEnvironment()) {
            AppUpdateStore.Apple(appStoreUrl)
        } else if (currentVersion < latestStoreVersion) {
            AppUpdateStore.Google(appStoreUrl)
        } else if (currentVersion < updateConfig.latestVersionCDN) {
            AppUpdateStore.CDN(updateConfig.cdnApkUrl)
        } else {
            // in this case, there is a minimum version requirement but no update available
            // in any store so cannot force update
            null
        }

        return when (store) {
            null -> AppUpdateStatus.UpToDate
            else -> AppUpdateStatus.UpdateAvailable(updateType, store)
        }
    }
}