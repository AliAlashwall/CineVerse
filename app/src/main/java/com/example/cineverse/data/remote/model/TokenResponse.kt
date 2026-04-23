package com.example.cineverse.data.remote.model


data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)
