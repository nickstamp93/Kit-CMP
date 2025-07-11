package com.nickstamp.kit.feature.analytics.domain

import com.nickstamp.kit.feature.analytics.domain.model.AnalyticsEvent

interface Analytics {
    fun sendEvent(event: AnalyticsEvent)
    fun setUserId(userId: String?)
    fun setUserProperty(key: String, value: String?)
    fun enable()
    fun disable()
    fun isEnabled(): Boolean
}