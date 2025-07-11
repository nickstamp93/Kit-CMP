package com.nickstamp.kit.core.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

expect fun httpClient(): HttpClient

object JsonConfig {
    val instance = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}