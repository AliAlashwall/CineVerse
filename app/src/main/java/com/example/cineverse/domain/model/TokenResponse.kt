package com.example.cineverse.domain.model

data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)