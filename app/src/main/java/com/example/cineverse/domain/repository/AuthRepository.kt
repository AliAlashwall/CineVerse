package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.LoginResponseDTO
import com.example.cineverse.domain.model.RequestTokenResponseDTO
import io.ktor.client.HttpClient

interface AuthRepository {
    suspend fun fetchRequestToken(client: HttpClient): RequestTokenResponseDTO


    suspend fun login(
        client: HttpClient, username: String,
        password: String,
        requestToken: String
    ): LoginResponseDTO
}