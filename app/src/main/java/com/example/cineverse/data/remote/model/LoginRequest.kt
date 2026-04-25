package com.example.cineverse.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
    @SerialName("request_token")
    val requestToken: String
)