package com.nickstamp.kit.feature.config.domain.usecase.configuration

import com.nickstamp.kit.feature.config.domain.Configurator
import com.nickstamp.kit.feature.config.domain.model.AppIntroConfig

class GetAppIntroConfigUseCase(
    private val configurator: Configurator
) {
    suspend operator fun invoke(): AppIntroConfig? {
        return configurator.getCachedConfiguration()?.appIntroConfig
    }
}