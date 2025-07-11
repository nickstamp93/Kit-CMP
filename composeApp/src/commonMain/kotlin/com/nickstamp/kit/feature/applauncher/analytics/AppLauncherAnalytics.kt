package com.nickstamp.kit.feature.applauncher.analytics

import com.nickstamp.kit.core.analytics.model.AnalyticsEvent

class AppLauncherAnalytics(): AnalyticsEvent() {

    override val category = "AppLauncher"

    companion object {

        fun getErrorTryAgainEvent(): AnalyticsEvent {
            return AppLauncherAnalytics().apply {
                metadata.putString(ACTION,"error_try_again_click")
            }
        }

        fun getUpdateLaterEvent(): AnalyticsEvent {
            return AppLauncherAnalytics().apply {
                metadata.putString(ACTION,"update_later_click")
            }
        }

        fun getCloseAppEvent(): AnalyticsEvent {
            return AppLauncherAnalytics().apply {
                metadata.putString(ACTION,"close_app_click")
            }
        }

        fun getUpdateAppEvent(): AnalyticsEvent {
            return AppLauncherAnalytics().apply {
                metadata.putString(ACTION,"update_app_click")
            }
        }

        fun getAnnouncementCtaEvent(): AnalyticsEvent {
            return AppLauncherAnalytics().apply {
                metadata.putString(ACTION,"announcement_cta_click")
            }
        }

    }

}