package com.nickstamp.kit.core.network

import com.nickstamp.kit.core.network.createHttpClient
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun httpClient(): HttpClient = createHttpClient().config {
    engine {
        OkHttp
    }
}