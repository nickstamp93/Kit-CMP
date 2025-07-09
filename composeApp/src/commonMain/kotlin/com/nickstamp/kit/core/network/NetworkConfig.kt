package com.nickstamp.kit.core.network

object NetworkConfig {
    const val DEFAULT_BASE_URL = "https://api.yourapp.com/v1/"
    const val CDN_BASE_URL = "https://cdn.yourapp.com/"
    const val AUTH_BASE_URL = "https://auth.yourapp.com/"
    
    val namedBaseUrls = mapOf(
        "default" to DEFAULT_BASE_URL,
        "cdn" to CDN_BASE_URL,
        "auth" to AUTH_BASE_URL
    )
    
    fun getBaseUrl(name: String): String {
        return namedBaseUrls[name] ?: DEFAULT_BASE_URL
    }
}