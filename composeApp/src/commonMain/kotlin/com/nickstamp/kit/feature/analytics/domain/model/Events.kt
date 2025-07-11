package com.nickstamp.kit.feature.analytics.domain.model

/**
 * Feature-organized analytics events for the framework
 */
object Events {

    /**
     * Core UI interaction events
     */
    fun buttonClicked(buttonName: String, screenName: String, buttonType: String? = null) =
        AnalyticsEvent.ButtonClicked(buttonName, screenName, buttonType)

    fun screenViewed(screenName: String, source: String? = null, parameters: Map<String, String>? = null) =
        AnalyticsEvent.ScreenViewed(screenName, source, parameters)

    /**
     * Content interaction events
     */
    fun itemSelected(itemName: String, itemId: String, itemType: String, source: String? = null, position: Int? = null) =
        AnalyticsEvent.ItemSelected(itemName, itemId, itemType, source, position)

    // Content type helpers
    fun movieSelected(movieName: String, movieId: String, source: String? = null, position: Int? = null) =
        itemSelected(movieName, movieId, "movie", source, position)

    fun articleSelected(articleName: String, articleId: String, source: String? = null, position: Int? = null) =
        itemSelected(articleName, articleId, "article", source, position)

    /**
     * Search events
     */
    fun searchPerformed(query: String, screenName: String, resultCount: Int? = null) =
        AnalyticsEvent.SearchPerformed(query, screenName, resultCount)

    /**
     * App lifecycle events
     */
    fun featureUsed(featureName: String, screenName: String, parameters: Map<String, String>? = null) =
        AnalyticsEvent.FeatureUsed(featureName, screenName, parameters)

    fun settingChanged(settingName: String, oldValue: String?, newValue: String) =
        AnalyticsEvent.SettingChanged(settingName, oldValue, newValue)

    /**
     * Network events
     */
    fun apiRequest(endpoint: String, method: String, responseCode: Int? = null, duration: Long? = null) =
        AnalyticsEvent.ApiRequest(endpoint, method, responseCode, duration)

    /**
     * Error events
     */
    fun errorOccurred(errorType: String, errorMessage: String, screenName: String) =
        AnalyticsEvent.ErrorOccurred(errorType, errorMessage, screenName)

    /**
     * Framework-specific events organized by feature
     */
    object Framework {
        fun themeChanged(newTheme: String, oldTheme: String) =
            settingChanged("theme", oldTheme, newTheme)

        fun introCompleted(duration: Long) =
            featureUsed("intro", "intro", mapOf("duration" to duration.toString()))

        fun showcaseViewed(source: String? = null) =
            screenViewed("showcase", source)

        fun developerToolsAccessed(screenName: String) =
            featureUsed("developer_tools", screenName)
    }

    /**
     * Custom event builder for flexibility
     */
    fun customEvent(category: String, action: String, parameters: Map<String, Any>) =
        AnalyticsEvent.Custom(category, action, parameters)
}