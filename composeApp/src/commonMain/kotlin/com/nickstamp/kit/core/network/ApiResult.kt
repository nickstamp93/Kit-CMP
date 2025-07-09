package com.nickstamp.kit.core.network

import io.ktor.http.*

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: ApiException) : ApiResult<Nothing>()
    
    inline fun <R> map(transform: (T) -> R): ApiResult<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
    }
    
    inline fun onSuccess(action: (T) -> Unit): ApiResult<T> = also {
        if (this is Success) action(data)
    }
    
    inline fun onError(action: (ApiException) -> Unit): ApiResult<T> = also {
        if (this is Error) action(exception)
    }
}

sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    data class NetworkError(val originalException: Throwable) : ApiException(
        "Network error: ${originalException.message}",
        originalException
    )
    
    data class HttpError(val statusCode: HttpStatusCode, val errorBody: String?) : ApiException(
        "HTTP ${statusCode.value} error: ${errorBody ?: statusCode.description}"
    )
    
    data class SerializationError(val originalException: Throwable) : ApiException(
        "Serialization error: ${originalException.message}",
        originalException
    )
    
    data class UnknownError(val originalException: Throwable) : ApiException(
        "Unknown error: ${originalException.message}",
        originalException
    )
}