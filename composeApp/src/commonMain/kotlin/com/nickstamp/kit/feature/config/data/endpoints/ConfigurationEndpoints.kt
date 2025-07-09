package com.nickstamp.kit.feature.config.data.endpoints

import com.nickstamp.kit.core.network.EndpointBuilder

object ConfigurationEndpoints {
    // Endpoint constants
    const val BASE_URL =
        "https://gist.githubusercontent.com/nickstamp93/eecc35645ec891c0260ceb038fd07514/raw/"
    const val CONFIGURATION = "config_gr.json"

    // Factory methods for complex endpoints
    fun getConfiguration(): EndpointBuilder {
        return EndpointBuilder.create(
            baseUrl = BASE_URL,
            endpoint = CONFIGURATION
        )
    }
}