package com.nickstamp.kit.feature.analytics.di

import com.nickstamp.kit.feature.analytics.data.repository.NoOpAnalytics
import com.nickstamp.kit.feature.analytics.domain.Analytics
import com.nickstamp.kit.feature.analytics.domain.usecase.SendAnalyticsEventUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val analyticsModule = module {
    // Analytics implementation - Replace NoOpAnalytics with your actual implementation
    single<Analytics> { NoOpAnalytics() }

    // Use cases
    singleOf(::SendAnalyticsEventUseCase)
}