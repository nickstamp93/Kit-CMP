package com.nickstamp.kit.feature.config.domain.model

data class Configuration(
    val upToDate: Boolean,
    val appUpdateConfig: AppUpdateConfig,
    val appRateConfig: AppRateConfig,
    val adPlacements: List<AdPlacementConfig>,
    val appLaunchAnnouncement: Announcement,
    val appIntroConfig: AppIntroConfig
)