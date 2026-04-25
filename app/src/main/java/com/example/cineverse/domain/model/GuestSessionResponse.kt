package com.example.cineverse.domain.model

data class GuestSessionResponse(
    val expiresAt: String,
    val guestSessionId: String,
    val success: Boolean
)