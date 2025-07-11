package com.nickstamp.kit.core.analytics

import com.nickstamp.kit.core.analytics.model.AnalyticsEvent

interface Analytics {
    fun sendEvent(event: AnalyticsEvent)
    fun setUserProperty(key: String, value: String?)
    fun enable()
    fun disable()
    fun isEnabled(): Boolean
}