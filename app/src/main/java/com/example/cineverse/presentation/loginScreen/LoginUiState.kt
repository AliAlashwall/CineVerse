package com.example.cineverse.presentation.loginScreen

import androidx.compose.ui.text.input.PasswordVisualTransformation


data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val accessToken: String? = null,
)