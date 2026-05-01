package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.GuestSessionResponse
import com.example.cineverse.domain.model.LoginResponse
import com.example.cineverse.domain.model.TokenResponse
import com.example.cineverse.domain.util.Result
import io.ktor.client.HttpClient

interface AuthRepository {
    suspend fun fetchRequestToken(client: HttpClient): Result<TokenResponse>


    suspend fun login(
        client: HttpClient, username: String,
        password: String,
        requestToken: String
    ): Result<LoginResponse>

    suspend fun joinAsGuest(client: HttpClient): Result<GuestSessionResponse>
}