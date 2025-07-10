package com.nickstamp.kit.feature.config.data.mapper

import com.nickstamp.kit.feature.config.data.dto.AdPlacementConfigDto
import com.nickstamp.kit.feature.config.data.dto.AnnouncementDto
import com.nickstamp.kit.feature.config.data.dto.AppIntroConfigDto
import com.nickstamp.kit.feature.config.data.dto.AppRateConfigDto
import com.nickstamp.kit.feature.config.data.dto.AppUpdateConfigDto
import com.nickstamp.kit.feature.config.data.dto.ConfigurationDto
import com.nickstamp.kit.feature.config.data.dto.CtaActionDto
import com.nickstamp.kit.feature.config.domain.model.AdPlacementConfig
import com.nickstamp.kit.feature.config.domain.model.Announcement
import com.nickstamp.kit.feature.config.domain.model.AppIntroConfig
import com.nickstamp.kit.feature.config.domain.model.AppRateConfig
import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig
import com.nickstamp.kit.feature.config.domain.model.Configuration
import com.nickstamp.kit.feature.config.domain.model.CtaAction

fun ConfigurationDto.toDomain(): Configuration {
    return Configuration(
        upToDate = upToDate,
        appUpdateConfig = appUpdateConfig.toDomain(),
        appRateConfig = appRateConfig.toDomain(),
        adPlacements = adPlacements.map { it.toDomain() },
        appLaunchAnnouncement = appLaunchAnnouncement.toDomain(),
        appIntroConfig = appIntroConfig.toDomain()
    )
}

fun AppUpdateConfigDto.toDomain(): AppUpdateConfig {
    return AppUpdateConfig(
        minimumRequiredVersion = minimumRequiredVersion,
        latestVersionGoogle = latestVersionGoogle,
        latestVersionApple = latestVersionApple,
        latestVersionCDN = latestVersionCDN,
        cdnApkUrl = cdnApkUrl
    )
}

fun AppRateConfigDto.toDomain(): AppRateConfig {
    return AppRateConfig(
        enabled = enabled
    )
}

fun AdPlacementConfigDto.toDomain(): AdPlacementConfig {
    return AdPlacementConfig(
        id = id,
        enabled = enabled,
        size = size,
        debounceMinutes = debounceMinutes,
        firstOffset = firstOffset,
        adSpacing = adSpacing
    )
}

fun AnnouncementDto.toDomain(): Announcement {
    return Announcement(
        message = message,
        action = action.toDomain()
    )
}

fun AppIntroConfigDto.toDomain(): AppIntroConfig {
    return AppIntroConfig(
        enabled = enabled,
        termsText = termsText
    )
}

fun CtaActionDto.toDomain(): CtaAction {
    return CtaAction(
        text = text,
        url = url,
        enabled = true
    )
}