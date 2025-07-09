package com.nickstamp.kit.feature.config.domain.usecase.configuration

import com.nickstamp.kit.feature.config.domain.Configurator
import com.nickstamp.kit.feature.config.domain.model.AppUpdateConfig

class GetAppUpdateConfigUseCase(
    private val configurator: Configurator
) {
    suspend operator fun invoke(): AppUpdateConfig? {
        return configurator.getCachedConfiguration()?.appUpdateConfig
    }
}