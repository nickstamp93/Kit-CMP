package com.nickstamp.kit.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun httpClient(): HttpClient = createHttpClient().config {
    engine {
        Darwin
    }
}