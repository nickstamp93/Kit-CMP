package com.nickstamp.kit.feature.config.data.endpoints

import com.nickstamp.kit.core.network.EndpointBuilder

object ConfigurationEndpoints {
    // TODO: Replace with your configuration API endpoint
    // This is a placeholder URL - configure this for your app
    // Endpoint constants
    const val BASE_URL =
        "https://gist.githubusercontent.com/nickstamp93/30e67698a5417b970f64352b37082da9/raw/"
    const val CONFIGURATION = "config.json"

    // Factory methods for complex endpoints
    fun getConfiguration(): EndpointBuilder {
        return EndpointBuilder.create(
            baseUrl = BASE_URL,
            endpoint = CONFIGURATION
        )
    }
}