package com.example.cineverse.domain.model

data class LoginResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)
