package com.nickstamp.kit.feature.analytics.domain.model

sealed class AnalyticsEvent(
    val category: String,
    val action: String
) {
    
    // UI interaction events
    data class ButtonClicked(
        val buttonName: String,
        val screenName: String,
        val buttonType: String? = null
    ) : AnalyticsEvent("ui", "button_clicked")
    
    data class ScreenViewed(
        val screenName: String,
        val source: String? = null,
        val parameters: Map<String, String>? = null
    ) : AnalyticsEvent("navigation", "screen_viewed")
    
    // Content interaction events
    data class ItemSelected(
        val itemName: String,
        val itemId: String,
        val itemType: String,
        val source: String? = null,
        val position: Int? = null
    ) : AnalyticsEvent("content", "item_selected")
    
    // Search events
    data class SearchPerformed(
        val query: String,
        val screenName: String,
        val resultCount: Int? = null
    ) : AnalyticsEvent("search", "search_performed")
    
    // App lifecycle events
    data class FeatureUsed(
        val featureName: String,
        val screenName: String,
        val parameters: Map<String, String>? = null
    ) : AnalyticsEvent("app", "feature_used")
    
    data class SettingChanged(
        val settingName: String,
        val oldValue: String?,
        val newValue: String
    ) : AnalyticsEvent("app", "setting_changed")
    
    // Network events
    data class ApiRequest(
        val endpoint: String,
        val method: String,
        val responseCode: Int? = null,
        val duration: Long? = null
    ) : AnalyticsEvent("network", "api_request")
    
    // Error events
    data class ErrorOccurred(
        val errorType: String,
        val errorMessage: String,
        val screenName: String
    ) : AnalyticsEvent("error", "error_occurred")
    
    // Custom events for flexibility
    data class Custom(
        val customCategory: String,
        val customAction: String,
        val parameters: Map<String, Any>
    ) : AnalyticsEvent(customCategory, customAction)
}