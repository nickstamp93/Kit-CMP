package com.nickstamp.kit.core.analytics.domain.usecase

import com.nickstamp.kit.core.analytics.model.AnalyticsEvent
import com.nickstamp.kit.core.analytics.Analytics

class SendAnalyticsEventUseCase(
    private val analytics: Analytics
) {
    operator fun invoke(event: AnalyticsEvent) {
        analytics.sendEvent(event)
    }
}