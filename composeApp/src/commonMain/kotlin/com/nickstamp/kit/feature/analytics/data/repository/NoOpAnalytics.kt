package com.nickstamp.kit.feature.analytics.data.repository

import com.nickstamp.kit.feature.analytics.domain.model.AnalyticsEvent

class NoOpAnalytics : AnalyticsEngine() {
    
    override fun logEvent(event: AnalyticsEvent) {
        // No-op implementation for testing/debugging
        // Example of how to use the helper method in concrete implementations:
        // val parameters = extractEventParameters(event)
        // analyticsLibrary.logEvent(event.category + "_" + event.action, parameters)
    }
    
    override fun setUserIdInternal(userId: String?) {
        // No-op implementation
    }
    
    override fun setUserPropertyInternal(key: String, value: String?) {
        // No-op implementation
    }
}