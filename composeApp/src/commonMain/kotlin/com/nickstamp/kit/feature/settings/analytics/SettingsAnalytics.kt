package com.nickstamp.kit.feature.settings.analytics

import com.nickstamp.kit.core.analytics.model.AnalyticsEvent

class SettingsAnalytics : AnalyticsEvent() {

    override val category = "Settings"

    companion object {

        fun getThemeChangedEvent(
            isDark: Boolean
        ): SettingsAnalytics {
            return SettingsAnalytics().apply {
                metadata.putString(ACTION, "set_theme")
                metadata.putString(LABEL, if (isDark) "dark" else "light")
            }
        }
    }

}