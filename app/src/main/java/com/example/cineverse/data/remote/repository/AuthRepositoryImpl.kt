package com.example.cineverse.data.remote.repository

import com.example.cineverse.data.remote.model.GuestSessionResponseDTO
import com.example.cineverse.data.remote.model.LoginRequest
import com.example.cineverse.data.remote.model.LoginResponseDTO
import com.example.cineverse.domain.repository.AuthRepository
import com.example.cineverse.data.remote.model.RequestTokenResponseDTO
import com.example.cineverse.presentation.loginScreen.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun fetchRequestToken(client: HttpClient): Result<RequestTokenResponseDTO> {
        return try {
            val response = client.get("authentication/token/new").body<RequestTokenResponseDTO>()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred")
        }
    }

    override suspend fun login(
        client: HttpClient,
        username: String,
        password: String,
        requestToken: String
    ): Result<LoginResponseDTO> {
        return try {
            val loginRequest = LoginRequest(username, password, requestToken)
            val response = client.post("authentication/token/validate_with_login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }.body<LoginResponseDTO>()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred")
        }
    }

    override suspend fun joinAsGuest(client: HttpClient): Result<GuestSessionResponseDTO> {
        return try {
            val response = client.get("authentication/guest_session/new").body<GuestSessionResponseDTO>()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred")
        }
    }
}