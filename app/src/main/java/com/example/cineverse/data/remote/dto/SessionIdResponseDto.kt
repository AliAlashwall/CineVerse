package com.example.cineverse.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionIdResponseDto(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("success")
    val success: Boolean
)