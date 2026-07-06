package com.example.cineverse.data.remote.repository

import android.util.Log
import com.example.cineverse.data.remote.dto.account.AccountDetailsDto
import com.example.cineverse.data.remote.mapper.toDomain
import com.example.cineverse.data.remote.util.HttpErrorHandler
import com.example.cineverse.domain.model.AccountDetails
import com.example.cineverse.domain.repository.AccountRepository
import com.example.cineverse.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    override suspend fun getAccountDetails(
        client: HttpClient,
        sessionId: String
    ): Result<AccountDetails> {
        return try {
            val accountResponse = client.get("account") {
                parameter("session_id", sessionId)
            }.body<AccountDetailsDto>()

            Log.d("AccountRepositoryImpl", "Fetched account details successfully")
            Result.Success(accountResponse.toDomain())
        } catch (e: Exception) {
            HttpErrorHandler.handleException(e, "Failed to fetch account details")
        }
    }
}
