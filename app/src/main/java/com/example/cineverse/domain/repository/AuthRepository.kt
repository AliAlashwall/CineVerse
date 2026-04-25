package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.GuestSessionResponseDTO
import com.example.cineverse.domain.model.LoginResponseDTO
import com.example.cineverse.domain.model.RequestTokenResponseDTO
import com.example.cineverse.presentation.loginScreen.Result
import io.ktor.client.HttpClient

interface AuthRepository {
    suspend fun fetchRequestToken(client: HttpClient): Result<RequestTokenResponseDTO>


    suspend fun login(
        client: HttpClient, username: String,
        password: String,
        requestToken: String
    ): Result<LoginResponseDTO>

    suspend fun joinAsGuest(client: HttpClient): Result<GuestSessionResponseDTO>

}