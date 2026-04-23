package com.example.cineverse.data.model


data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)
