package com.nickstamp.kit.core.network

class EndpointBuilder private constructor(
    private val baseUrl: String,
    private val endpoint: String,
    private val queryParams: Map<String, String> = emptyMap()
) {
    
    companion object {
        fun create(
            endpoint: String,
            baseUrl: String? = null
        ): EndpointBuilder {
            val effectiveBaseUrl = baseUrl ?: NetworkConfig.DEFAULT_BASE_URL
            return EndpointBuilder(effectiveBaseUrl, endpoint)
        }
        
        fun createWithNamedBaseUrl(
            endpoint: String,
            baseUrlName: String
        ): EndpointBuilder {
            val baseUrl = NetworkConfig.getBaseUrl(baseUrlName)
            return EndpointBuilder(baseUrl, endpoint)
        }
    }
    
    fun withQueryParams(params: Map<String, String>): EndpointBuilder {
        return EndpointBuilder(baseUrl, endpoint, params)
    }
    
    fun withQueryParam(key: String, value: String): EndpointBuilder {
        return EndpointBuilder(baseUrl, endpoint, queryParams + (key to value))
    }
    
    fun withPathParam(placeholder: String, value: String): EndpointBuilder {
        val newEndpoint = endpoint.replace("{$placeholder}", value)
        return EndpointBuilder(baseUrl, newEndpoint, queryParams)
    }
    
    fun build(): String {
        return if (endpoint.startsWith("http")) {
            addQueryParams(endpoint)
        } else {
            val cleanBaseUrl = baseUrl.removeSuffix("/")
            val cleanEndpoint = endpoint.removePrefix("/")
            addQueryParams("$cleanBaseUrl/$cleanEndpoint")
        }
    }
    
    private fun addQueryParams(url: String): String {
        if (queryParams.isEmpty()) return url
        
        val separator = if (url.contains("?")) "&" else "?"
        val queryString = queryParams.entries.joinToString("&") { (key, value) ->
            "$key=$value"
        }
        return "$url$separator$queryString"
    }
}

// Extension functions for common patterns
fun String.toEndpoint(baseUrl: String? = null): EndpointBuilder {
    return EndpointBuilder.create(this, baseUrl)
}

fun String.toEndpointWithBaseUrl(baseUrlName: String): EndpointBuilder {
    return EndpointBuilder.createWithNamedBaseUrl(this, baseUrlName)
}