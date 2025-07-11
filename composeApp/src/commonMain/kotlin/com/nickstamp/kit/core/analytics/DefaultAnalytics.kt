package com.nickstamp.kit.core.analytics

import com.nickstamp.kit.core.analytics.model.AnalyticsEvent

class DefaultAnalytics : Analytics {

    override fun sendEvent(event: AnalyticsEvent) {
        println("Event: $event")
    }

    override fun setUserProperty(key: String, value: String?) {
        TODO("Not yet implemented")
    }

    override fun enable() {
        TODO("Not yet implemented")
    }

    override fun disable() {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}