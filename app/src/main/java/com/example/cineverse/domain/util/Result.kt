package com.example.cineverse.domain.util

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()
    
    sealed class Error(open val message: String) : Result<Nothing>() {
        data class NetworkError(override val message: String = "No internet connection") : Error(message)
        data class TimeoutError(override val message: String = "Request timeout") : Error(message)
        data class ServerError(val code: Int, override val message: String) : Error(message)
        data class ParsingError(override val message: String = "Failed to parse response") : Error(message)
        data class UnknownError(override val message: String) : Error(message)
    }
}

inline fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> data
    else -> null
}

inline fun <T> Result<T>.isSuccess(): Boolean = this is Result.Success

inline fun <T> Result<T>.isError(): Boolean = this is Result.Error

inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> = when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Error -> this
    is Result.Loading -> Result.Loading
    is Result.Empty -> Result.Empty
}