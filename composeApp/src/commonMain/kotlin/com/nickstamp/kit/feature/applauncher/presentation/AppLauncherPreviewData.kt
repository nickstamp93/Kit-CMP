package com.nickstamp.kit.feature.applauncher.presentation

import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig
import com.nickstamp.kit.feature.config.domain.model.PlatformUpdateConfig

object AppLauncherPreviewData {

    fun getAppUpdateConfig(
        iosMinimumRequiredVersion: Int = 0,
        iosLatestVersion: Int = 0,
        iosDownloadUrl: String = "",
        androidMinimumRequiredVersion: Int = 0,
        androidLatestVersion: Int = 0,
        androidDownloadUrl: String = ""
    ) = AppUpdateConfig(
        ios = PlatformUpdateConfig(
            minimumRequiredVersion = iosMinimumRequiredVersion,
            latestVersion = iosLatestVersion,
            downloadUrl = iosDownloadUrl
        ),
        android = PlatformUpdateConfig(
            minimumRequiredVersion = androidMinimumRequiredVersion,
            latestVersion = androidLatestVersion,
            downloadUrl = androidDownloadUrl
        )
    )

}