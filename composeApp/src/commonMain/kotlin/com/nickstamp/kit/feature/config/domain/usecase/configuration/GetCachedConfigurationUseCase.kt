package com.nickstamp.kit.feature.config.domain.usecase.configuration

import com.nickstamp.kit.feature.config.domain.Configurator
import com.nickstamp.kit.feature.config.domain.model.Configuration

class GetCachedConfigurationUseCase(
    private val configurator: Configurator
) {
    suspend operator fun invoke(): Configuration? {
        return configurator.getCachedConfiguration()
    }
}