package com.example.cineverse.data.remote.repository

import com.example.cineverse.domain.model.LoginRequest
import com.example.cineverse.domain.model.LoginResponseDTO
import com.example.cineverse.domain.repository.AuthRepository
import com.example.cineverse.domain.model.RequestTokenResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun fetchRequestToken(client: HttpClient): RequestTokenResponseDTO {
        return client.get("authentication/token/new").body()
    }

    override suspend fun login(
        client: HttpClient,
        username: String,
        password: String,
        requestToken: String
    ): LoginResponseDTO {
        return client.post("authentication/token/validate_with_login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username, password, requestToken))
        }.body()
    }
}