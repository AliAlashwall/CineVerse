package com.example.cineverse.domain.repository

import com.example.cineverse.domain.model.AccountDetails
import io.ktor.client.HttpClient
import com.example.cineverse.domain.util.Result

interface AccountRepository {

    suspend fun getAccountDetails(
        client: HttpClient,
        sessionId: String
    ): Result<AccountDetails>
}