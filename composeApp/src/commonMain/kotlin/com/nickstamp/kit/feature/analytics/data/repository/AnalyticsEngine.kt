package com.nickstamp.kit.feature.analytics.data.repository

import com.nickstamp.kit.feature.analytics.domain.Analytics
import com.nickstamp.kit.feature.analytics.domain.model.AnalyticsEvent

abstract class AnalyticsEngine : Analytics {
    
    private var isAnalyticsEnabled = true
    private var currentUserId: String? = null
    private val userProperties = mutableMapOf<String, String?>()

    override fun sendEvent(event: AnalyticsEvent) {
        if (!isAnalyticsEnabled) return
        
        try {
            logEvent(event)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    override fun setUserId(userId: String?) {
        if (!isAnalyticsEnabled) return
        
        currentUserId = userId
        try {
            setUserIdInternal(userId)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    override fun setUserProperty(key: String, value: String?) {
        if (!isAnalyticsEnabled) return
        
        userProperties[key] = value
        try {
            setUserPropertyInternal(key, value)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    override fun enable() {
        isAnalyticsEnabled = true
        onAnalyticsEnabled()
    }

    override fun disable() {
        isAnalyticsEnabled = false
        onAnalyticsDisabled()
    }

    override fun isEnabled(): Boolean = isAnalyticsEnabled

    protected fun getCurrentUserId(): String? = currentUserId
    
    protected fun getUserProperties(): Map<String, String?> = userProperties.toMap()

    protected fun extractEventParameters(event: AnalyticsEvent): Map<String, Any> {
        val params = mutableMapOf<String, Any>()
        params["category"] = event.category
        params["action"] = event.action
        
        when (event) {
            is AnalyticsEvent.ButtonClicked -> {
                params["button_name"] = event.buttonName
                params["screen_name"] = event.screenName
                event.buttonType?.let { params["button_type"] = it }
            }
            is AnalyticsEvent.ScreenViewed -> {
                params["screen_name"] = event.screenName
                event.source?.let { params["source"] = it }
                event.parameters?.let { params.putAll(it) }
            }
            is AnalyticsEvent.ItemSelected -> {
                params["item_name"] = event.itemName
                params["item_id"] = event.itemId
                params["item_type"] = event.itemType
                event.source?.let { params["source"] = it }
                event.position?.let { params["position"] = it }
            }
            is AnalyticsEvent.SearchPerformed -> {
                params["query"] = event.query
                params["screen_name"] = event.screenName
                event.resultCount?.let { params["result_count"] = it }
            }
            is AnalyticsEvent.FeatureUsed -> {
                params["feature_name"] = event.featureName
                params["screen_name"] = event.screenName
                event.parameters?.let { params.putAll(it) }
            }
            is AnalyticsEvent.SettingChanged -> {
                params["setting_name"] = event.settingName
                event.oldValue?.let { params["old_value"] = it }
                params["new_value"] = event.newValue
            }
            is AnalyticsEvent.ApiRequest -> {
                params["endpoint"] = event.endpoint
                params["method"] = event.method
                event.responseCode?.let { params["response_code"] = it }
                event.duration?.let { params["duration"] = it }
            }
            is AnalyticsEvent.ErrorOccurred -> {
                params["error_type"] = event.errorType
                params["error_message"] = event.errorMessage
                params["screen_name"] = event.screenName
            }
            is AnalyticsEvent.Custom -> {
                params.putAll(event.parameters)
            }
        }
        
        return params
    }

    protected abstract fun logEvent(event: AnalyticsEvent)
    
    protected abstract fun setUserIdInternal(userId: String?)
    
    protected abstract fun setUserPropertyInternal(key: String, value: String?)
    
    protected open fun onAnalyticsEnabled() {
        // Override in subclasses if needed
    }
    
    protected open fun onAnalyticsDisabled() {
        // Override in subclasses if needed
    }
    
    protected open fun handleException(exception: Exception) {
        // Override in subclasses for custom error handling
        // Default implementation silently ignores exceptions
    }
}