package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.RequestTokenResponseDTO
import io.ktor.client.HttpClient

interface Repository {
    suspend fun fetchRequestToken(client: HttpClient): RequestTokenResponseDTO
}