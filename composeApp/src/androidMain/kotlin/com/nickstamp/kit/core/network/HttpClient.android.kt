package com.nickstamp.kit.core.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

actual fun httpClient(): HttpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(JsonConfig.instance)
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("HttpClient", message)
            }
        }
    }
}