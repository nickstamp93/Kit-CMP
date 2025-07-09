package com.nickstamp.kit.core.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

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
        return httpClient.get(url).body()
    }

    suspend inline fun <reified T> post(
        endpoint: String,
        body: Any,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend inline fun <reified T> put(
        endpoint: String,
        body: Any,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return httpClient.put(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend inline fun <reified T> delete(
        endpoint: String,
        baseUrl: String? = null
    ): T {
        val url = EndpointBuilder.create(endpoint, baseUrl).build()
        return httpClient.delete(url).body()
    }

    // Overloaded methods for more advanced endpoint building
    suspend inline fun <reified T> get(endpointBuilder: EndpointBuilder): T {
        return httpClient.get(endpointBuilder.build()).body()
    }

    suspend inline fun <reified T> post(endpointBuilder: EndpointBuilder, body: Any): T {
        return httpClient.post(endpointBuilder.build()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend inline fun <reified T> put(endpointBuilder: EndpointBuilder, body: Any): T {
        return httpClient.put(endpointBuilder.build()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend inline fun <reified T> delete(endpointBuilder: EndpointBuilder): T {
        return httpClient.delete(endpointBuilder.build()).body()
    }
}