package com.nickstamp.kit.core.network

import com.nickstamp.kit.core.network.createHttpClient
import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun httpClient(): HttpClient = createHttpClient().config {
    engine {
        Darwin
    }
}