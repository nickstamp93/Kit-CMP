package com.nickstamp.kit.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(
    @PublishedApi
    internal val httpClient: HttpClient
) {
    suspend inline fun <reified T> get(
        endpoint: String,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return httpClient.get(url).body()
    }

    suspend inline fun <reified T> get(
        endpoint: String,
        params: Map<String, String>,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl)
            .withQueryParams(params)
            .build()
        return JsonConfig.instance.decodeFromString<T>(httpClient.get(url).bodyAsText())
    }

    suspend inline fun <reified T> post(
        endpoint: String,
        body: Any,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return JsonConfig.instance.decodeFromString<T>(httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText())
    }

    suspend inline fun <reified T> put(
        endpoint: String,
        body: Any,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return JsonConfig.instance.decodeFromString<T>(httpClient.put(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText())
    }

    suspend inline fun <reified T> delete(
        endpoint: String,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return JsonConfig.instance.decodeFromString<T>(httpClient.delete(url).bodyAsText())
    }

    // Overloaded methods for more advanced endpoint building
    suspend inline fun <reified T> get(endpointBuilder: EndpointBuilder): T {
        return JsonConfig.instance.decodeFromString<T>(
            httpClient.get(endpointBuilder.build()).bodyAsText()
        )
    }

    suspend inline fun <reified T> post(endpointBuilder: EndpointBuilder, body: Any): T {
        return JsonConfig.instance.decodeFromString<T>(httpClient.post(endpointBuilder.build()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText())
    }

    suspend inline fun <reified T> put(endpointBuilder: EndpointBuilder, body: Any): T {
        return JsonConfig.instance.decodeFromString<T>(httpClient.put(endpointBuilder.build()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText())
    }

    suspend inline fun <reified T> delete(endpointBuilder: EndpointBuilder): T {
        return JsonConfig.instance.decodeFromString<T>(
            httpClient.delete(endpointBuilder.build()).body()
        )
    }
}