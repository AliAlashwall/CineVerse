package com.example.cineverse.data.remote.util

import com.example.cineverse.domain.util.Result
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import kotlinx.serialization.SerializationException

object HttpErrorHandler {
    fun handleException(exception: Exception, defaultMessage: String): Result.Error {
        return when (exception) {
            is ConnectTimeoutException, is SocketTimeoutException -> {
                Result.Error.TimeoutError("Request timed out. Please try again.")
            }
            is ResponseException -> {
                val statusCode = exception.response.status.value
                val statusMessage = exception.response.status.description
                when (statusCode) {
                    in 400..499 -> Result.Error.ServerError(
                        code = statusCode,
                        message = "Client error ($statusCode): $statusMessage"
                    )
                    in 500..599 -> Result.Error.ServerError(
                        code = statusCode,
                        message = "Server error ($statusCode): $statusMessage"
                    )
                    else -> Result.Error.ServerError(
                        code = statusCode,
                        message = statusMessage
                    )
                }
            }
            is SerializationException -> {
                Result.Error.ParsingError("Failed to parse server response. Please try again.")
            }
            is java.net.UnknownHostException, is java.net.SocketException -> {
                Result.Error.NetworkError("No internet connection. Please check your network.")
            }
            else -> {
                Result.Error.UnknownError(exception.localizedMessage ?: defaultMessage)
            }
        }
    }
}
