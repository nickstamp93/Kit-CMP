package com.nickstamp.kit.feature.applauncher.presentation

import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig

object AppLauncherPreviewData {

    fun getAppUpdateConfig(
        minimumRequiredVersion: Int = 0,
        latestVersionGoogle: Int = 0,
        latestVersionApple: Int = 0,
        latestVersionCDN: Int = 0,
        cdnApkUrl: String = ""
    ) = AppUpdateConfig(
        minimumRequiredVersion = minimumRequiredVersion,
        latestVersionGoogle = latestVersionGoogle,
        latestVersionApple = latestVersionApple,
        latestVersionCDN = latestVersionCDN,
        cdnApkUrl = cdnApkUrl
    )

}