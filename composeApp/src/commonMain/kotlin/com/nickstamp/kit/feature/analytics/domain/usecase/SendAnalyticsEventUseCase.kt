package com.nickstamp.kit.feature.analytics.domain.usecase

import com.nickstamp.kit.feature.analytics.domain.Analytics
import com.nickstamp.kit.feature.analytics.domain.model.AnalyticsEvent

class SendAnalyticsEventUseCase(
    private val analytics: Analytics
) {
    operator fun invoke(event: AnalyticsEvent) {
        analytics.sendEvent(event)
    }
}