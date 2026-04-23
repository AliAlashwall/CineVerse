package com.example.cineverse.data.remote.repository

import com.example.cineverse.domain.repository.Repository
import com.example.cineverse.domain.model.RequestTokenResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override suspend fun fetchRequestToken(client: HttpClient): RequestTokenResponseDTO {
        return client.get("authentication/token/new").body()
    }
}