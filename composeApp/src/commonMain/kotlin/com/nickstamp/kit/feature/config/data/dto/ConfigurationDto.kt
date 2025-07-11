package com.nickstamp.kit.feature.config.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDto(
    @SerialName("utd") internal val _upToDate: Boolean? = false,
    @SerialName("u") internal val _appUpdateConfig: AppUpdateConfigDto? = AppUpdateConfigDto(),
    @SerialName("r") internal val _appRateConfig: AppRateConfigDto? = AppRateConfigDto(),
    @SerialName("a") internal val _adPlacements: List<AdPlacementConfigDto>? = null,
    @SerialName("ala") internal val _appLaunchAnnouncement: AnnouncementDto? = AnnouncementDto(),
    @SerialName("i") internal val _appIntroConfig: AppIntroConfigDto? = AppIntroConfigDto()
) {
    val upToDate: Boolean
        get() = _upToDate ?: false
    val appUpdateConfig: AppUpdateConfigDto
        get() = _appUpdateConfig ?: AppUpdateConfigDto()
    val appRateConfig: AppRateConfigDto
        get() = _appRateConfig ?: AppRateConfigDto()
    val adPlacements: List<AdPlacementConfigDto>
        get() = _adPlacements.orEmpty()
    val appLaunchAnnouncement: AnnouncementDto
        get() = _appLaunchAnnouncement ?: AnnouncementDto()
    val appIntroConfig: AppIntroConfigDto
        get() = _appIntroConfig ?: AppIntroConfigDto()
}