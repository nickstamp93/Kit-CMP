package com.nickstamp.kit.core.network

import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException

suspend inline fun <T> safeApiCall(crossinline apiCall: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (exception: Exception) {
        ApiResult.Error(exception.toApiException())
    }
}

fun Exception.toApiException(): ApiException {
    return when (this) {
        is ResponseException -> {
            ApiException.HttpError(
                statusCode = response.status,
                errorBody = null
            )
        }
        is SerializationException -> {
            ApiException.SerializationError(this)
        }
        else -> {
            ApiException.UnknownError(this)
        }
    }
}