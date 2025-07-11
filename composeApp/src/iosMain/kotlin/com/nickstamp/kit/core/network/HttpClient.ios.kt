package com.nickstamp.kit.core.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import platform.Foundation.NSLog

actual fun httpClient(): HttpClient = HttpClient(Darwin) {
    install(ContentNegotiation) {
        json(JsonConfig.instance)
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                NSLog("HttpClient: %s", message)
            }
        }
    }
}